/**
 * Copyright (C) 2007-2011, Jens Lehmann
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

package org.dllearner.configuration.spring.editors;

import org.semanticweb.owlapi.model.*;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;

/**
 * Basic Property Editor for OWL entity.
 * @author Lorenz Buehmann
 */
public class OWLEntityEditor<T extends EntityType> extends AbstractPropertyEditor<OWLEntity> {

	private final OWLDataFactory df = new OWLDataFactoryImpl();

    private T entityType;

    public OWLEntityEditor(T entityType) {
        this.entityType = entityType;
    }

    @Override
    public String getJavaInitializationString() {
        return value.toStringID();
    }

    @Override
    public String getAsText() {
        return value.toStringID();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        value = df.getOWLEntity(entityType, IRI.create(text));
    }
}
