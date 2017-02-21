package amznprep;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TempJob {
	
	private Job[] jobs = null;
	private int filled = 0;
	
	
	public TempJob(int jobsNumber){
		jobs= new Job[jobsNumber];
	}

	public Job[] getJobs() {
		return jobs;
	}

	public void addJob(Job job) {
		this.jobs[filled] = job;
		filled++;
	}
	
	public void quicksort(int lo, int hi) {
		int p;
		if (lo < hi) {
			p = partition(lo, hi);
			quicksort(lo, p - 1);
			quicksort(p + 1, hi);
		}
	}
	
	public int partition(int lo, int hi){
		int comp = 0;
		Job t;
		Job p = jobs[hi];
		int i = lo - 1;
		for (int j = lo; j <= hi - 1; j++) {
			comp = jobs[j].compareTo(p);
			if (comp <= 0) {
				i++;
				t = jobs[i];
				jobs[i] = jobs[j];
				jobs[j] = t;
				printJobs(jobs);
			}
		}
		t = jobs[i + 1];
		jobs[i + 1] = jobs[hi];
		jobs[hi] = t;
		printJobs(jobs);
		return i + 1;
	}
	
	void printJobs(Job[] j) {
		Job[] jobs = j;
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		for (int i=0; i<jobs.length; i++) {
			sb.append(jobs[i].getDays()).append("|").append(sdf.format(jobs[i].getBegin())).append(",").
			append(sdf.format(jobs[i].getEnd())).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public Job[] getSelected() {
		List<Job> selected = new ArrayList<Job>();
 		quicksort(0, jobs.length - 1);
 		boolean overlaped;
		for (int i=0; i < jobs.length; i++) {
			overlaped = false;
			if (i == 0) {
				selected.add(jobs[i]);
			} else {
				for (Job j:selected) {
					if ( (jobs[i].getBegin().getTime() >= j.getBegin().getTime() &&
							jobs[i].getBegin().getTime() <= j.getEnd().getTime()) ||
							(jobs[i].getEnd().getTime() >= j.getBegin().getTime() &&
									jobs[i].getEnd().getTime() <= j.getEnd().getTime()) ) {
						overlaped = true;
						break;
					} 
				}
				if (!overlaped) {
					selected.add(jobs[i]);
					overlaped = false;
				}
			}
		}
		Job[] selectedJobs = new Job[selected.size()];
		for (int k=0; k < selected.size(); k++) {
			selectedJobs[k] = (Job)selected.get(k);
		}
		return selectedJobs;
	}
	
	
	public static void main(String[] args) {
		TempJob tj = new TempJob(5);

		try {
			tj.addJob(new Job("02-02-2017", "08-02-2017"));
			tj.addJob(new Job("02-01-2017", "06-01-2017"));
			tj.addJob(new Job("06-01-2017", "06-01-2017"));
			tj.addJob(new Job("01-01-2017", "05-01-2017"));
			tj.addJob(new Job("05-01-2017", "06-01-2017"));
			tj.printJobs(tj.getJobs());
			Job[] selected = tj.getSelected();
			System.out.println("Selected:");
			tj.printJobs(selected);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
}


class Job implements Comparable<Job>{
	Date begin = null;
	Date end = null;
	int days;
	
	Job (String begin, String end) throws ParseException {
		SimpleDateFormat format =  new SimpleDateFormat("dd-MM-yyyy");
		this.begin = format.parse(begin);
		this.end = format.parse(end);
		
		long t1 = this.begin.getTime();
		long t2 = this.end.getTime();
		
		days = (int)Math.abs((t1 - t2)/(1000*60*60*24));
		
	}

	@Override
	public int compareTo(Job job) {
		int comparisson = 0;
		long timeDiff;
		if (days < job.getDays()) {
			comparisson = -1;
		} else if (days == job.getDays()) {
			timeDiff = begin.getTime() - job.getBegin().getTime();
			if (timeDiff < 0) {
				comparisson = -1;
			} else if (timeDiff == 0) {
				comparisson = 0;
			} else {
				comparisson = 1;
			}
			
		} else if (days > job.getDays()) {
			comparisson = 1;
		}
		return comparisson;
	}
	
	public Date getBegin() {
		return begin;
	}

	public Date getEnd() {
		return end;
	}

	public int getDays() {
		return days;
	}
}
