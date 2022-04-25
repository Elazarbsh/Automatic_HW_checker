public class DateUtils {

	public static enum DateType {
		GREGORIAN, JEWISH
	};

	private static final int DAYS_IN_MONTH[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static int getLastDayOfGregorianMonth(int month, int year) {
		if ((month == 2) && ((year % 4) == 0) && ((year % 400) != 100) && ((year % 400) != 200)
				&& ((year % 400) != 300))
			return 29;
		else
			return DAYS_IN_MONTH[month - 1];
	}

	public static int absoluteFromGregorianDate(GregorianDate date) {
		int value, m;

		/* Days so far this month */
		value = date.getDay();

		/* Days in prior months this year */
		for (m = 1; m < date.getMonth(); m++)
			value += getLastDayOfGregorianMonth(m, date.getYear());

		/* Days in prior years */
		value += (365 * (date.getYear() - 1));

		/* Julian leap days in prior years ... */
		value += ((date.getYear() - 1) / 4);

		/* ... minus prior century years ... */
		value -= ((date.getYear() - 1) / 100);

		/* ... plus prior years divisible by 400 */
		value += ((date.getYear() - 1) / 400);

		return (value);
	}

	public static GregorianDate gregorianDateFromAbsolute(int absDate) {
		int approx, y, m, day, month, year, temp;

		/* Approximation */
		approx = absDate / 366;

		/* Search forward from the approximation */
		y = approx;
		for (;;) {
			temp = absoluteFromGregorianDate(new GregorianDate(1, 1, y + 1));
			if (absDate < temp)
				break;
			y++;
		}
		year = y;

		/* Search forward from January */
		m = 1;
		for (;;) {
			temp = absoluteFromGregorianDate(new GregorianDate(getLastDayOfGregorianMonth(m, year), m, year));
			if (absDate <= temp)
				break;
			m++;
		}
		month = m;

		/* Calculate the day by subtraction */
		temp = absoluteFromGregorianDate(new GregorianDate(1, month, year));
		day = absDate - temp + 1;

		return new GregorianDate(day, month, year);
	}

	public static boolean hebrewLeapYear(int year) {
		if ((((year * 7) + 1) % 19) < 7)
			return true;
		else
			return false;
	}

	public static int getLastMonthOfJewishYear(int year) {
		if (hebrewLeapYear(year))
			return 13;
		else
			return 12;
	}

	public static int getLastDayOfJewishMonth(int month, int year) {
		if ((month == 2) || (month == 4) || (month == 6) || (month == 10) || (month == 13))
			return 29;
		if ((month == 12) && (!hebrewLeapYear(year)))
			return 29;
		if ((month == 8) && (!longHeshvan(year)))
			return 29;
		if ((month == 9) && (shortKislev(year)))
			return 29;
		return 30;
	}

	private static int hebrewCalendarElapsedDays(int year) {
		int value, monthsElapsed, partsElapsed, hoursElapsed;
		int day, parts, alternativeDay;

		/* Months in complete cycles so far */
		value = 235 * ((year - 1) / 19);
		monthsElapsed = value;

		/* Regular months in this cycle */
		value = 12 * ((year - 1) % 19);
		monthsElapsed += value;

		/* Leap months this cycle */
		value = ((((year - 1) % 19) * 7) + 1) / 19;
		monthsElapsed += value;

		partsElapsed = (((monthsElapsed % 1080) * 793) + 204);
		hoursElapsed = (5 + (monthsElapsed * 12) + ((monthsElapsed / 1080) * 793) + (partsElapsed / 1080));

		/* Conjunction day */
		day = 1 + (29 * monthsElapsed) + (hoursElapsed / 24);

		/* Conjunction parts */
		parts = ((hoursElapsed % 24) * 1080) + (partsElapsed % 1080);

		/* If new moon is at or after midday, */
		if ((parts >= 19440) ||

		/* ...or is on a Tuesday... */
				(((day % 7) == 2) &&
				/* at 9 hours, 204 parts or later */
						(parts >= 9924) &&
						/* of a common year */
						(!hebrewLeapYear(year)))
				||

				/* ...or is on a Monday at... */
				(((day % 7) == 1) &&
				/* 15 hours, 589 parts or later... */
						(parts >= 16789) &&
						/* at the end of a leap year */
						(hebrewLeapYear(year - 1))))
			/* Then postpone Rosh HaShanah one day */
			alternativeDay = day + 1;
		else
			alternativeDay = day;

		/* If Rosh HaShanah would occur on Sunday, Wednesday, */
		/* or Friday */
		if (((alternativeDay % 7) == 0) || ((alternativeDay % 7) == 3) || ((alternativeDay % 7) == 5))
			/* Then postpone it one (more) day and return */
			alternativeDay++;

		return (alternativeDay);
	}

	private static int daysInHebrewYear(int year) {
		return (hebrewCalendarElapsedDays(year + 1) - hebrewCalendarElapsedDays(year));
	}

	private static boolean longHeshvan(int year) {
		if ((daysInHebrewYear(year) % 10) == 5)
			return true;
		else
			return false;
	}

	private static boolean shortKislev(int year) {
		if ((daysInHebrewYear(year) % 10) == 3)
			return true;
		else
			return false;
	}

	public static int absoluteFromJewishDate(JewishDate date) {
		int value, returnValue, m;

		/* Days so far this month */
		value = date.getDay();
		returnValue = value;

		/* If before Tishrei */
		if (date.getMonth() < 7) {
			/* Then add days in prior months this year before and */
			/* after Nisan. */
			for (m = 7; m <= getLastMonthOfJewishYear(date.getYear()); m++) {
				value = getLastDayOfJewishMonth(m, date.getYear());
				returnValue += value;
			}
			for (m = 1; m < date.getMonth(); m++) {
				value = getLastDayOfJewishMonth(m, date.getYear());
				returnValue += value;
			}
		} else {
			for (m = 7; m < date.getMonth(); m++) {
				value = getLastDayOfJewishMonth(m, date.getYear());
				returnValue += value;
			}
		}

		/* Days in prior years */
		value = hebrewCalendarElapsedDays(date.getYear());
		returnValue += value;

		/* Days elapsed before absolute date 1 */
		value = 1373429;
		returnValue -= value;

		return (returnValue);
	}

	public static JewishDate jewishDateFromAbsolute(int absDate) {
		int approx, y, m, year, month, day, temp, start;

		/* Approximation */
		approx = (absDate + 1373429) / 366;

		/* Search forward from the approximation */
		y = approx;
		for (;;) {
			temp = absoluteFromJewishDate(new JewishDate(1, 7, (y + 1)));
			if (absDate < temp)
				break;
			y++;
		}
		year = y;

		/* Starting month for search for month */
		temp = absoluteFromJewishDate(new JewishDate(1, 1, year));
		if (absDate < temp)
			start = 7;
		else
			start = 1;

		/* Search forward from either Tishri or Nisan */
		m = start;
		for (;;) {
			temp = absoluteFromJewishDate(new JewishDate((getLastDayOfJewishMonth(m, year)), m, year));
			if (absDate <= temp)
				break;
			m++;
		}
		month = m;

		/* Calculate the day by subtraction */
		temp = absoluteFromJewishDate(new JewishDate(1, month, year));
		day = absDate - temp + 1;

		return new JewishDate(day, month, year);
	}
}
