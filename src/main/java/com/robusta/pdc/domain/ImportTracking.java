package com.robusta.pdc.domain;

/**
 * Import statement tracking.
 *
 * <p>Allows tracking visitors to visit the content
 * tracked.</p>
 *
 * <p>Implementation can chose what to do with the import
 * statements that are tracked.</p>
 */
public interface ImportTracking {
    /**
     * Accept an {@link ImportStatement import statement} of a
     * {@link SourceFile java source file } that needs to be tracked.
     *
     * @param importStatement ImportStatement
     */
    void track(ImportStatement importStatement);

    /**
     * Allow a tracking visitor to visit the tracked content.
     *
     * <p>Tracked content refers to the import statements that
     * have been tracked as part of the
     * {@link #track(ImportStatement) track} operation.</p>
     *
     * @param visitor Visitor
     */
    public void allowVisitor(Visitor visitor);

    /**
     * Import tracking visitor.
     */
    interface Visitor {
        /**
         * Called back with the source file and the java package of
         * the tracked package for the visitor to perform its job.
         *
         * @param sourceFile SourceFile
         * @param javaPackage JavaPackage
         */
        void visit(SourceFile sourceFile, JavaPackage javaPackage);
    }
}
