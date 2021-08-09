package com.wcqj.taiyi;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/forecast")
@Produces(MediaType.APPLICATION_JSON)
public class ForecastResource {
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	    @GET
	    @Path("/year")
	    public String getYearYao(@QueryParam("birthDate") String birthDate,@QueryParam("fDate") String fDate) throws ParseException {
	    	Calendar calBirth=Calendar.getInstance();;
			calBirth.setTime(sdf.parse(birthDate));
			Calendar cal=Calendar.getInstance();
			cal.setTime(sdf.parse(fDate));
	    	
	        return Taiyi.getYearYao(calBirth, cal);
	    }
	    
	    @GET
	    @Path("/month")
	    public String getMonthYao(@QueryParam("birthDate") String birthDate,@QueryParam("fDate") String fDate) throws ParseException {
	    	Calendar calBirth=Calendar.getInstance();;
			calBirth.setTime(sdf.parse(birthDate));
			Calendar cal=Calendar.getInstance();;
			cal.setTime(sdf.parse(fDate));
	    	
	        return Taiyi.getMonthYao(calBirth, cal);
	    }
	    
	    @GET
	    @Path("/date")
	    public String getDateYao(@QueryParam("birthDate") String birthDate,@QueryParam("fDate") String fDate) throws ParseException {
	    	Calendar calBirth=Calendar.getInstance();;
			calBirth.setTime(sdf.parse(birthDate));
			Calendar cal=Calendar.getInstance();;
			cal.setTime(sdf.parse(fDate));
	    	
	        return Taiyi.getDateYao(calBirth, cal);
	    }

}
