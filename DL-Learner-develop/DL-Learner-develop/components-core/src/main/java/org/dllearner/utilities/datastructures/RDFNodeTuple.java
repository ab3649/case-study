/**
 * Copyright (C) 2007 - 2016, Jens Lehmann
 *
 * This file is part of DL-Learner.
 *
 * DL-Learner is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * DL-Learner is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dllearner.utilities.datastructures;

import com.google.common.collect.ComparisonChain;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.sparql.util.NodeComparator;
import org.dllearner.kb.extraction.LiteralNode;

/**
 * A container which can hold two Strings, mainly used as a helper.
 * Also used as pre form, if you want to create triple, that have the same subject
 * @author Sebastian Hellmann
 */
public class RDFNodeTuple implements Comparable<RDFNodeTuple>{

	public RDFNode a;
	public RDFNode b;

	public RDFNodeTuple(RDFNode a, RDFNode b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "<" + a.toString() + "|" + b.toString() + ">";
	}

	public boolean equals(RDFNodeTuple t) {
		return b.equals(t.b) && a.equals(t.a);
	}

	@Override
	public int compareTo(RDFNodeTuple t) {
		NodeComparator comparator = new NodeComparator();
		return ComparisonChain.start().
				compare(a.asNode(), t.a.asNode(), comparator).
				compare(b.asNode(), t.b.asNode(), comparator).
				result();
	}
	
	public boolean aPartContains(String partOf) {
		return a.toString().contains(partOf);
	}
	
	public boolean bPartContains(String partOf) {
		return b.toString().contains(partOf);
	}
	
	
	public String getNTriple (String subject){
		String ret = "<"+subject+"> ";
		ret+="<"+a.toString()+"> ";
		if(b.isLiteral()){
			ret+=new LiteralNode(b).getNTripleForm();
			
		}else{
			ret+="<"+b.toString()+"> ";
		}
		ret+=".";
		return ret;
	}

}
