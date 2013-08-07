package com.robusta.pdc.domain;

import com.google.common.base.Function;
import com.google.common.base.Splitter;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.transform;


/**
 * @author sudhir.ravindramohan
 */
public class SourceFolders implements Iterable<SourceFolder> {
    private final Iterable<SourceFolder> folders;

    static final Splitter SPLIT_BY_COMMA_WITH_TRIM = Splitter.on(",").omitEmptyStrings().trimResults();

    public SourceFolders(String commaSeparatedFoldersList) {
        this(toList(commaSeparatedFoldersList));
    }

    protected static Iterable<String> toList(String commaSeperatedStringList) {
        checkArgument(!isNullOrEmpty(commaSeperatedStringList), "Source Folders cannot be initialized. A valid argument (not null or empty) will be required");
        return SPLIT_BY_COMMA_WITH_TRIM.split(commaSeperatedStringList);
    }

    public SourceFolders(Iterable<String> folders) {
        this.folders = transform(folders, new Function<String, SourceFolder>() {
            @Override
            public SourceFolder apply(String sourceFolder) {
                return new SourceFolder(sourceFolder);
            }
        });
    }

    @Override
    public Iterator<SourceFolder> iterator() {
        return folders.iterator();
    }
}
