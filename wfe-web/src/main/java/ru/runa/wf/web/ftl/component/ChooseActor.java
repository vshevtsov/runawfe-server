/*
 * This file is part of the RUNA WFE project.
 * 
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation; version 2.1 
 * of the License. 
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Lesser General Public License for more details. 
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this program; if not, write to the Free Software 
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package ru.runa.wf.web.ftl.component;

import ru.runa.wfe.commons.ftl.FormComponent;
import ru.runa.wfe.presentation.BatchPresentation;
import ru.runa.wfe.presentation.BatchPresentationFactory;
import ru.runa.wfe.service.delegate.Delegates;
import ru.runa.wfe.var.dto.WfVariable;
import freemarker.template.TemplateModelException;

/**
 * @deprecated code moved to {@link InputVariable}.
 * 
 * @author dofs
 * @since 4.0
 */
@Deprecated
public class ChooseActor extends FormComponent {

    private static final long serialVersionUID = 1L;

    @Override
    protected Object renderRequest() throws Exception {
        String actorVarName = getParameterAsString(0);
        String view = getParameterAsString(1);
        if ("all".equals(view)) {
            WfVariable variable = variableProvider.getVariableNotNull(actorVarName);
            return ViewUtil.createExecutorSelect(user, variable);
        } else if ("raw".equals(view)) {
            BatchPresentation batchPresentation = BatchPresentationFactory.ACTORS.createNonPaged();
            int[] sortIds = { 1 };
            boolean[] sortOrder = { true };
            batchPresentation.setFieldsToSort(sortIds, sortOrder);
            return Delegates.getExecutorService().getExecutors(user, batchPresentation);
        } else {
            throw new TemplateModelException("Unexpected value of VIEW parameter: " + view);
        }
    }

}
