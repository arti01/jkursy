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
public class DrzGrupa extends NamedNode implements TreeNode{
    private static final long serialVersionUID = 1L;
    private DrzMaster drzMaster;
    private Long id;
    private List<DrzUrzad>drzUrzad=new ArrayList<DrzUrzad>();

    public DrzGrupa(String name, Long id, String opis) {
        super(name, id, opis);
        type="grupa";
    }
    
    @Override
    public TreeNode getChildAt(int childIndex) {
        return drzUrzad.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return drzUrzad.size();
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
        return drzUrzad.isEmpty();
    }

    @Override
    public Enumeration<DrzUrzad> children() {
       return Iterators.asEnumeration(drzUrzad.iterator());
    }

    public DrzMaster getDrzMaster() {
        return drzMaster;
    }

    public void setDrzMaster(DrzMaster drzMaster) {
        this.drzMaster = drzMaster;
    }

    public List<DrzUrzad> getDrzUrzad() {
        return drzUrzad;
    }

    public void setDrzUrzad(List<DrzUrzad> drzUrzad) {
        this.drzUrzad = drzUrzad;
    }
    
}
