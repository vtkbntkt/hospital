package ua.nure.gudkov.ST4.converter;

import java.sql.Date;

import ua.nure.gudkov.ST4.bean.form.AddServiceFormBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Service;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.Status;

/**
 * Add service form bean to serviceHistory entity converter.
 * 
 * @author A.Gudkov
 *
 */
public class AddServiceFormBeanToServiceHistory implements Converter<AddServiceFormBean, ServiceHistory> {

	@Override
	public ServiceHistory convert(AddServiceFormBean addForm) {
		Date currentDate = new Date(new java.util.Date().getTime());
		ServiceHistory servHistory = new ServiceHistory();
		servHistory.setDateRecord(currentDate);
		servHistory.setIdAnamnesis(Integer.parseInt(addForm.getIdAnamnesis()));
		servHistory.setIdService(Service.valueOf(addForm.getServiceType().toUpperCase()).ordinal());
		servHistory.setIdStatus(Status.TAKEN.ordinal());
		servHistory.setServiceValue(addForm.getServiceValue());
		return servHistory;
	}

}
