package top.genouka.autr;

import top.genouka.autr.Layer.ParatranzLayer;
import top.genouka.autr.Layer.SpecLayer;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("QiuTr -l <path>");
            return;
        }
        if (args[0].equals("-l")) {
            File f = new File(args[1]);
            if (f.isFile()) {
                try {
                    String str = FileHandler.getContentByPath(f.getAbsoluteFile().toPath());
                    SpecLayer specLayer = new ParatranzLayer();
                    if (f.getName().endsWith(".txt")) {
                        FileHandler.setContentByPath(new File(f.getParentFile(), f.getName() + ".paratranz.json").getAbsoluteFile().toPath(), specLayer.spec2tjson(str));
                    } else if (f.getName().endsWith(".json")) {
                        FileHandler.setContentByPath(new File(f.getParentFile(), f.getName() + ".txt").getAbsoluteFile().toPath(), specLayer.tjson2translation(str));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (f.isDirectory()) {
                File nf = new File(f, "layer_mod_output");
                nf.mkdirs();
                SpecLayer specLayer = new ParatranzLayer();
                for (File i : f.listFiles()) {
                    if (i.isFile()) {
                        try {
                            String str = FileHandler.getContentByPath(i.getAbsoluteFile().toPath());
                            if (i.getName().endsWith(".txt")) {
                                FileHandler.setContentByPath(new File(nf, i.getName() + ".paratranz.json").getAbsoluteFile().toPath(), specLayer.spec2tjson(str));
                            } else if (i.getName().endsWith(".json")) {
                                FileHandler.setContentByPath(new File(nf, i.getName() + ".txt").getAbsoluteFile().toPath(), specLayer.tjson2translation(str));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
