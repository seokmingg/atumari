package org.example.atumari.festival.service;

import java.time.LocalDate;
import java.util.List;

import org.example.atumari.festival.dao.FestivalDao;
import org.example.atumari.festival.dto.FestivalDto;

public class FestivalCardService {

	private FestivalDao festivalDao;

	public FestivalCardService() {
		festivalDao = new FestivalDao();
	}

	public List<FestivalDto> getThisMonthFestivalList() {

		LocalDate now = LocalDate.now();

		LocalDate firstDay = now.withDayOfMonth(1);

		LocalDate nextMonth = firstDay.plusMonths(1);

		return festivalDao.getThisMonthFestivalList(firstDay, nextMonth);
	}
}