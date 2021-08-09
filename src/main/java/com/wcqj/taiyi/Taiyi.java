package com.wcqj.taiyi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.wcqj.taiyi.utils.BaZi;
import com.wcqj.taiyi.utils.Gua;
import com.wcqj.taiyi.utils.Lunar;

public class Taiyi {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String birthDate = "2019-12-12 00:00";
		String fDate = "2048-1-22 22:00";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Calendar calBirth=Calendar.getInstance();;
		calBirth.setTime(sdf.parse(birthDate));
		Calendar cal=Calendar.getInstance();;
		cal.setTime(sdf.parse(fDate));
		
		getYearGua(calBirth, cal);;
		getMonthYao(calBirth, cal);
		getDateYao(calBirth, cal);
			
	}
	
	public static String getYearYao(Calendar calBirth, Calendar cal) throws ParseException {
		int yearGua = getYearGua(calBirth, cal);
		return String.valueOf(yearGua);
	}
	
	public static int getYearGua(Calendar calBirth, Calendar cal) throws ParseException {
		
        int year = calBirth.get(Calendar.YEAR);
		int fYear = cal.get(Calendar.YEAR);
		
//		System.out.println("出生时间:"+calBirth);
		//出生卦
		int chushenGua=Gua.getChushen(calBirth);
//		System.out.println("预测时间:"+cal);
		Gua.getChushen(cal);
		
//		System.out.println("出身卦:" + chushenGua);
        //年卦=出身卦数+岁数=求和（如果相加大于64，就用和除以64，商数取整数，余数即为年卦数）
		
		int age = fYear - year;
		int yearGua = (chushenGua +age) % 64 == 0 ? 64 : (chushenGua +age) % 64;
//		System.out.println("年卦:"+yearGua);
		return yearGua;
	}
	
	public static String getMonthYao(Calendar calBirth, Calendar cal) throws ParseException {
		//月卦：此处求的月卦都是阴历月份
		//用年卦数+2+月数=求和（如果相加大于64，就用和除以64，商数取整数，余数即为月卦数）
		int yearGua = getYearGua(calBirth, cal);
		
		BaZi lunar = new BaZi(cal);
		int lunarMonth = lunar.getnumbermonth();
		
		int monthGua = (yearGua + 2 + lunarMonth) % 64 == 0 ? 64 : (yearGua + 2 + lunarMonth) % 64;
		System.out.println("阴历"+lunarMonth+"月动爻:"+Gua.monthYao(lunarMonth, monthGua));
		return Gua.monthYao(lunarMonth, monthGua);
	}
	
	public static String getDateYao(Calendar calBirth, Calendar cal) throws ParseException {
		
		int yearGua = getYearGua(calBirth, cal);
		BaZi lunar = new BaZi(cal);
		int lunarMonth = lunar.getnumbermonth();
		int monthGua = (yearGua + 2 + lunarMonth) % 64 == 0 ? 64 : (yearGua + 2 + lunarMonth) % 64;
		//日卦:用阴历月卦数+日期甲子序数，求和对64取余
		int sortDate = 0;
		Lunar lunaryue = new Lunar(cal.getTime());
        String ganziDate = lunaryue.getCyclicaDay();//日柱
        for(int i=0;i<BaZi.jiazhi.length;i++) {
        	if(ganziDate.equals(BaZi.jiazhi[i])) {
        		sortDate = i + 1;
        		break;
        	}
        }
		int dateGua = (monthGua + sortDate) % 64==0 ? 64 : (monthGua + sortDate) % 64;
		
		System.out.println("预测日卦:"+Gua.gua[dateGua-1][0]+",序号:"+dateGua+",动爻:"+Gua.dateYao(ganziDate, dateGua));
		
		return Gua.dateYao(ganziDate, dateGua);
		
	}

}
