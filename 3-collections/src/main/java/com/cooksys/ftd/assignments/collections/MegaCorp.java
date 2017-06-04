package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {
	  
	Set<Capitalist> capSet = new HashSet<Capitalist>();
	//Map<Capitalist, FatCat> capMap = new HashMap<Capitalist, FatCat>();
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
    	if (capitalist == null){
    		return false;
    	}
    	else if (has(capitalist)) {
    		return false;
    	}
    	else if (capitalist.hasParent()) {
    		if (!capSet.contains(capitalist.getParent())) {
    			capSet.add(capitalist.getParent());
    		}
    		capSet.add(capitalist);
    		return true;
    	}
    	else if ((!capitalist.hasParent()) && (capitalist instanceof FatCat) && (getChildren((FatCat)capitalist)) != null) {
    		capSet.add(capitalist);
    		return true;
    	}
    	else if ((!capitalist.hasParent()) && (capitalist instanceof WageSlave)) {
    		return false;
    	}
    	else return true;
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
    	if (capSet.contains(capitalist)) {
        	return true;
        }
        else return false;
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
    	return capSet;
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() { 
    	Set<FatCat> parentSet = new HashSet<FatCat>();
    	if (capSet.isEmpty()) {
    		return parentSet;
    	}  	
    	else {
    		for (Capitalist cap : capSet) {
    			if ((cap.hasParent()) && (!parentSet.contains(cap.getParent())))
    			parentSet.add(cap.getParent());
    		}
    		return parentSet;
    	}
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {  
		Set<Capitalist> childSet = new HashSet<Capitalist>();
		
		if (fatCat != null)
			for (Capitalist cap : capSet) {
				if (cap.getParent() == fatCat) {
				childSet.add(cap);
				}
			}

			return childSet;	
		}

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	Map<FatCat, Set<Capitalist>> hierarchyMap = new HashMap<FatCat, Set<Capitalist>>();
    	
    	for (FatCat parent : getParents()) {
    			hierarchyMap.put(parent, getChildren(parent));
    	}
    	return hierarchyMap;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
    	List<FatCat> catList= new ArrayList<FatCat>();
    	Capitalist currCap;
    	
    	if (!capitalist.hasParent()) {
    		return catList;
    	}
    	else {
    			currCap = capitalist;
    			
    			while(currCap != null) {
	    			catList.add(currCap.getParent());
	    			currCap = currCap.getParent();
	    		}
	    		return catList;
    	}
    }
    
}
