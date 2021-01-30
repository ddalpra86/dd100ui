package org.dalpra.fw.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.dalpra.fw.entity.Document;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@ApplicationScoped
public class DocumentService {
    
    public TreeNode createDocuments() {
        TreeNode root = new DefaultTreeNode(new Document("Files", "-", "Folder",""), null);

		TreeNode applications = new DefaultTreeNode(new Document("Applications", "-", "Folder",""), root);
		

		//Applications
		TreeNode dd100ui = new DefaultTreeNode(new Document("DD100UI", "-", "Folder",""), applications);
		TreeNode users = new DefaultTreeNode("app", new Document("users", "-", "Application", "users?faces-redirect=true"), dd100ui);
		TreeNode fruits = new DefaultTreeNode("app", new Document("fruits", "-", "Application", "fruits?faces-redirect=true"), dd100ui);

        return root;
    }
}