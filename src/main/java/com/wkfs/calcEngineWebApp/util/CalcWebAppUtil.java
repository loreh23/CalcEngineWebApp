package com.wkfs.calcEngineWebApp.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wkfs.calcEngineWebApp.model.Operation;
import com.wkfsfrc.ce.AppCore.Calculator;
import com.wkfsfrc.ce.Exception.InvalidStatementException;

public class CalcWebAppUtil {
	public static String changeIntoJson(String operands) {
		if (!operands.startsWith("[")) {
			operands = "[" + operands;
		}
		if (!operands.endsWith("]")) {
			operands = operands + "]";
		}
		return operands;
	}
	public static void calculate(JSONObject jObject,Calculator calculator, StringBuilder jsonBuilder,ObjectMapper mapper) {
		try {
			if (jObject.has("operation") && jObject.has("operands")
					&& jObject.getString("operation").length() > 1
					&& changeIntoJson(jObject.getString("operands")).length() > 2) {
				Operation op = new Operation(jObject.getString("operation"),
						changeIntoJson(jObject.getString("operands")));
				try {
					calculator.calculate(op.toString());
					op.setResult(calculator.getResult().substring(calculator.getResult().lastIndexOf(" ") + 1,
							calculator.getResult().length()));
					jsonBuilder.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(op));
					jsonBuilder.append(","+System.getProperty("line.separator"));

				} catch (InvalidStatementException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
