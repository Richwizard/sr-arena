package com.sr.gear;

public enum CyberwareTypes {
	NONE("None", 0)	{
		public double getEssLoss(int rating)	{
			return 0;
		}
		
		public String getDesc(int rating)	{
			return "No cyberware installed";
		}
	},
	BONELACING("Bone Lacing", 3)	{
		public double getEssLoss(int rating)	{
			return -0.5 * rating;
		}
		
		public String getDesc(int rating)	{
			return String.format("BOD+%1$d, AR+%1$d, STR+%1$dP, ESS-%2$.1f", rating, rating * 0.5);
		}
	},
	DERMAL_PLATING("Dermal Plating", 6)	{
		public double getEssLoss(int rating)	{
			return -0.5 * rating;
		}
		
		public String getDesc(int rating)	{
			return String.format("AR+%1$d, ESS-%2$.1f", rating, rating * 0.5);
		}
	},
	MUSCLE_REPLACEMENT("Muscle Replacement", 4)	{
		public double getEssLoss(int rating)	{
			return -1 * rating;
		}
		
		public String getDesc(int rating)	{
			return String.format("STR+%1$d, AGI+%1$d, ESS-%2$d", rating, rating * 1);
		}
	},
	REACTION_ENHANCERS("Reaction Enhancers", 3)	{
		public double getEssLoss(int rating)	{
			return -0.3 * rating;
		}
		
		public String getDesc(int rating)	{
			return String.format("REA+%1$d, ESS-%2$.1f", rating, rating * 0.3);
		}
	},
	CONTROL_RIG("Rigger Control Rig", 3)	{
		public double getEssLoss(int rating)	{
			return -1 * rating;
		}
		
		public String getDesc(int rating)	{
			return String.format("+%1$d to Vehicle skill tests, ESS-%2$d", rating, rating * 1);
		}
	},
	WIRED_REFLEXES("Wired Reflexes", 3)	{
		public double getEssLoss(int rating)	{
			if (rating == 1)
				return -2;
			if (rating == 2)
				return -3;
			if (rating == 3)
				return -5;
			else return 0;
		}
		
		public String getDesc(int rating)	{
			return String.format("REA+%1$d, INI+%1$dD6, ESS%2$.0f", rating, getEssLoss(rating));
		}
	};
	
//	BONELACING_ALUMINIUM("Aluminium Bone Lacing", "BOD+2, AR+2, STR+2P, ESS-1", 3),
//	BONELACING_TITANIUM("Titanium Bone Lacing", "BOD+3, AR+3, STR+3P, ESS-1.5", 3),
//	MUSCLE_REPLACEMENT_2("Muscle Replacement 2", "STR+2, AGI+2, ESS-2"),
//	MUSCLE_REPLACEMENT_3("Muscle Replacement 3", "STR+3, AGI+3, ESS-3"),
//	REACTION_ENHANCERS_2("Reaction Enhancers", "REA+2, ESS-0.6"),
//	REACTION_ENHANCERS_3("Reaction Enhancers", "REA+3, ESS-0.9"),
//	CONTROL_RIG_2("Rigger Control Rig 2", "+2 to Vehicle skill tests, ESS-2"),
//	CONTROL_RIG_3("Rigger Control Rig 3", "+3 to Vehicle skill tests, ESS-3"),
//	WIRED_REFLEXES_2("Wired Reflexes 2", "REA+2, INI+2D6, ESS-3"),	
//	WIRED_REFLEXES_3("Wired Reflexes 3", "REA+3, INI+3D6, ESS-5");
//	DERMAL_PLATING_2("Dermal Plating 2", "AR+2, ESS-1.0", 3),
//	DERMAL_PLATING_3("Dermal Plating 3", "AR+3, ESS-1.5", 3),
	
	private final String name;
	private final int maxRating;
	public abstract double getEssLoss(int rating);
	public abstract String getDesc(int rating);

	private CyberwareTypes(String name, int maxRating)	{
		this.name = name;
		this.maxRating = maxRating;
	}
	
	public String getName()	{
		return name;
	}
	
	public int getMaxRating()	{
		return maxRating;
	}
	
	@Override
	public String toString()	{
		return name;
	}
}
