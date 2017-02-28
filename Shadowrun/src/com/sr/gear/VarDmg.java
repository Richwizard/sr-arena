package com.sr.gear;

public enum VarDmg {
	NONE {
		@Override
		public int getVarDmg(int base)	{
			return 0;
		}
	},
	STR {
		@Override
		public int getVarDmg(int str)	{
			return str;
		}
	},
	STRPLUS1 {
		@Override
		public int getVarDmg(int str)	{
			return str + 1;
		}
	},
	STRPLUS2 {
		@Override
		public int getVarDmg(int str)	{
			return str + 2;
		}
	},
	STRPLUS3 {
		@Override
		public int getVarDmg(int str)	{
			return str + 3;
		}
	},
	STRPLUS5 {
		@Override
		public int getVarDmg(int str)	{
			return str + 5;
		}
	},
	RAPLUS5 {
		@Override
		public int getVarDmg(int rating)	{
			return rating + 5;
		}
	};
	
	public abstract int getVarDmg(int base);
}