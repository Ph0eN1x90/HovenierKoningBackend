package com.hovenierkoning.backend.event;

import com.hovenierkoning.backend.model.Tree;

public class TreeSavedEvent {
    private final Tree tree;
    
    public TreeSavedEvent(Tree tree) {
        this.tree = tree;
    }
    
    public Tree getTree() {
        return tree;
    }
}
