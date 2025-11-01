# QiuMvz2Translater
This tool is an external text file format conversion and translation utility customized by Qiuming for the MVZ2 GMS2 version.

It is recommended to download the translated files, perform the conversion using this tool, and place them in the "texts" folder within the game directory of MVZ2 GMS2 version.

**The original project also supported automatic translation and translation cache upload. However, as it contained personal tokens, it could not be made public, and the corresponding code has been removed.**

## How to Use This Program
Download the jar file from Release, then run `java -jar .\autr.main.jar -l <file path>`

The expected file path can be either a file or a directory.

**If it is a directory:**

Then process all .txt or .json files in the directory (excluding subdirectories)

**If it is a file:**

The file extension can be json or txt. If the input is json, the output will be txt; if the input is txt, the output will be json.

JSON is a file format suitable for the [Paratranz](https://paratranz.cn/projects/16795) translation platform.

The format is as follows:

```json5
[
  {
    "key":"ADVICE_TUTORIAL_01",
    "original":"左键点击器械卡牌选中器械！\r\n",
    "translation":"Left click on the instrument deck to select the instrument!\r\n"
  },
  //...
]
```

The txt file is an external text file for MVZ2.

The format is as follows:

```text
[ADVICE_TUTORIAL_01]
左键点击器械卡牌选中器械！

[ADVICE_TUTORIAL_02]
点击草坪放置器械！

[ADVICE_TUTORIAL_03]
干得漂亮！
```


## How to compile this project
Open the project in IntelliJ IDEA, then click "Build Artifacts," select "autr.main:jar0," and finally click "Build.".

![img.png](img.png)

![img_1.png](img_1.png)

Then the file `out/artifacts/autr_main_jar/autr.main.jar` will be generated.

## License
MPL2.0