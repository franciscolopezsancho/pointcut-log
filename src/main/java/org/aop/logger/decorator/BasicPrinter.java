package org.aop.logger.decorator;

import java.util.Iterator;
import java.util.List;

public class BasicPrinter extends Printer {

	@Override
	public String print(Object object) {
		StringBuilder result = new StringBuilder();

		if (object == null)
			result.append("<null>");
		else if (object instanceof String) {
			if (((String) object).length() > 100)
				result.append(((String) object).substring(0, 100) + "...[more]");
			else
				result.append((String) object);
		} else if (object instanceof Long)
			result.append(((Long) object).toString());
		else if (object instanceof Boolean)
			result.append(((Boolean) object).toString());
		else if (object instanceof Double)
			result.append(((Double) object).toString());
		else if (object instanceof Integer)
			result.append(((Integer) object).toString());
		else if (object instanceof List) {
			Iterator it = ((List) object).iterator();
			StringBuilder response = new StringBuilder().append("items { ");
			while (it.hasNext()) {
				Object next = it.next();
				final Class genericClass = next.getClass();
				response.append(print(genericClass.cast(next)));
				if (it.hasNext())
					response.append(" , ");
			}
			result.append(response.append(" }").toString());
		} else
			result.append("object of type " + object.getClass());

		return result.toString();
	}

}
