/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.eod2.managedUm;

import com.google.common.collect.Iterators;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;

/**
 *
 * @author arti01
 */
public class DrzUrzad extends NamedNode implements TreeNode{
    private static final long serialVersionUID = 1L;
    private DrzGrupa drzGrupa;

    public DrzUrzad() {
        setType("urzadz");
    }
    
    @Override
    public TreeNode getChildAt(int childIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public TreeNode getParent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIndex(TreeNode node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration<DrzUrzad> children() {
       return null;
    }

    public DrzGrupa getDrzGrupa() {
        return drzGrupa;
    }

    public void setDrzGrupa(DrzGrupa drzGrupa) {
        this.drzGrupa = drzGrupa;
    }

    
}
