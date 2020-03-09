package eLiq;

// Class for doing the calculations

public class Calc {
	int vol;
	final int baza = 200;
	double nic_vol;
	
	//Constructor
	
	Calc(int vol) {
		this.vol = vol;
	}
	
	//Method for returning value rounded to 1 decimal point
	static double round(double value) {
		return (double) Math.round(value * 10) / 10.0;
	}
	
	//Method for calculating amount of nicotine base to add to final mix
	double nic(int desired_conc) {
		double nic_amount;
		nic_amount = vol * desired_conc;
		double nic_base_vol = nic_amount / baza;
		nic_vol = round(nic_base_vol);
		return nic_vol;
	}
	
	//Method for volume of PG
	double PG(int PG_perc) {
		double PG_vol;
		PG_vol = ((vol * PG_perc)/100) - nic_vol;
		return PG_vol;
	}
	
	//Method for volume of VG
	double VG(int VG_perc) {
		double VG_vol;
		VG_vol = (vol * VG_perc)/100;
		return VG_vol;
	}
}
