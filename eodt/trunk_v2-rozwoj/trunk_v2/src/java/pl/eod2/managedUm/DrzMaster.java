/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.eod2.managedUm;

import com.google.common.collect.Iterators;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 *
 * @author arti01
 */
public class DrzMaster extends NamedNode implements TreeNode{
    private static final long serialVersionUID = 1L;
    private List<DrzGrupa>drzGrupa=new ArrayList<DrzGrupa>();

    public DrzMaster() {
        setType("master");
    }
    
    @Override
    public TreeNode getChildAt(int childIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getChildCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TreeNode getParent() {
       return null;
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
        return drzGrupa.isEmpty();
    }

    @Override
    public Enumeration<DrzGrupa> children() {
       return Iterators.asEnumeration(drzGrupa.iterator());
    }

    public List<DrzGrupa> getDrzGrupa() {
        return drzGrupa;
    }

    public void setDrzGrupa(List<DrzGrupa> drzGrupa) {
        this.drzGrupa = drzGrupa;
    }
    
}
