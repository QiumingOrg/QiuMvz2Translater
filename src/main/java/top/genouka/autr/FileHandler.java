package top.genouka.autr;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FileHandler {
    public static File chosseFile() {
        JFileChooser fileChooser = new JFileChooser(); // 创建文件选择器对象

        int result = fileChooser.showOpenDialog(null); // 打开文件选择对话框

        if (result == JFileChooser.APPROVE_OPTION) { // 用户选择了一个文件
            File selectedFile = fileChooser.getSelectedFile(); // 获取选择的文件
            return selectedFile;
        }
        return null;
    }

    public static String getContentByPath(Path path) throws IOException {
        BufferedReader rd = openTextFileR(path, "UTF-8");
        String content = rd.lines().collect(Collectors.joining("\r\n"));
        //byte[] fileBytes = Files.readAllBytes(path);
        //String content = new String(fileBytes);
        return content;
    }

    public static void setContentByPath(Path path, String str) throws IOException {
        Files.writeString(path, str, StandardCharsets.UTF_8);
    }

    public static BufferedReader openTextFileR(
            Path fileName
            , String charSet
    ) throws IOException {
        return new BufferedReader(
                new InputStreamReader(
                        skipUTF8BOM(
                                new FileInputStream(
                                        new File(fileName.toUri()))
                                , charSet)
                        , charSet));
    }

    public static InputStream skipUTF8BOM(
            InputStream is
            , String charSet
    ) throws IOException {
        if (!charSet.toUpperCase().equals("UTF-8")) return is;
        if (!is.markSupported()) {
            //   如果输入流不支持mark功能时，用BufferedInputStream替换InputStream
            is = new BufferedInputStream(is);
        }
        is.mark(3); // 标记先头三位
        if (is.available() >= 3) {
            byte b[] = {0, 0, 0};
            is.read(b, 0, 3);
            if (b[0] != (byte) 0xEF ||
                    b[1] != (byte) 0xBB ||
                    b[2] != (byte) 0xBF) {
                is.reset();// 如果文件不含有[BOM]位时，将文件指针复位
            }
        }
        return is;
    }
}