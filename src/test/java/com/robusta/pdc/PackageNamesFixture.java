package com.robusta.pdc;


import com.google.common.base.Joiner;
import com.robusta.pdc.domain.*;
import org.slf4j.LoggerFactory;

import java.io.File;

public abstract class PackageNamesFixture {
    public static final Joiner DIRECTORY_NAME_JOINER = Joiner.on(File.separatorChar).skipNulls();
    public static final PackageNames VALID_VALUES = new PackageNames("com.foo.bar, _com._foo._bar");
    public static final PackageNames INVALID_VALUES = new PackageNames("com.foo1.bar1, _com._foo1._bar1");
    public static final String ABS_PATH = pathToTestSourceFolder();
    public static final SourceFolders SOURCE_FOLDERS = new SourceFolders(ABS_PATH);
    public static final SourceFolder SOURCE_FOLDER = new SourceFolder(ABS_PATH);
    public static final String COM_DOT_FOO_DOT_BAR_PATH = DIRECTORY_NAME_JOINER.join(ABS_PATH, "com", "foo", "bar");
    public static final String COM_FOO_BAR_PATH = DIRECTORY_NAME_JOINER.join(ABS_PATH, "_com", "_foo", "_bar");
    public static final SourceFolderPackage COM_DOT_FOO_DOT_BAR = new SourceFolderPackage(SOURCE_FOLDER, new File(COM_DOT_FOO_DOT_BAR_PATH));
    public static final SourceFolderPackage COM_FOO_BAR = new SourceFolderPackage(SOURCE_FOLDER, new File(DIRECTORY_NAME_JOINER.join(ABS_PATH, "_com", "_foo", "_bar")));
    public static final SourceFile CLASS_A = new SourceFile(SOURCE_FOLDER, COM_FOO_BAR, new File(DIRECTORY_NAME_JOINER.join(COM_FOO_BAR_PATH, "ClassA.java")));
    public static final SourceFile CLASS_B = new SourceFile(SOURCE_FOLDER, COM_FOO_BAR, new File(DIRECTORY_NAME_JOINER.join(COM_FOO_BAR_PATH, "ClassB.java")));
    public static final SourceFile CLASS_C = new SourceFile(SOURCE_FOLDER, COM_DOT_FOO_DOT_BAR, new File(DIRECTORY_NAME_JOINER.join(COM_DOT_FOO_DOT_BAR_PATH, "ClassC.java")));
    public static final SourceFile CLASS_D = new SourceFile(SOURCE_FOLDER, COM_DOT_FOO_DOT_BAR, new File(DIRECTORY_NAME_JOINER.join(COM_DOT_FOO_DOT_BAR_PATH, "ClassD.java")));
    private static String pathToTestSourceFolder() {
        String absolutePath = new File(DIRECTORY_NAME_JOINER.join("src", "test", "resources", "var", "app", "code")).getAbsolutePath();
        LoggerFactory.getLogger(PackageNamesFixture.class).trace("absolutePath = " + absolutePath);
        return absolutePath;
    }

    public static String directoryName(String first, String second, String... rest) {
        return DIRECTORY_NAME_JOINER.join(first, second, rest);
    }
}
