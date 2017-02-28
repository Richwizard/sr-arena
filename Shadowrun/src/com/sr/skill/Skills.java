package com.sr.skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.List;

import com.sr.attribute.Attributes;
import com.sr.person.Role;

public enum Skills {
	NONE(0, "None", Attributes.AGILITY, SkillGroup.NONE)	{
		public int getDef(Role role)	{
			return 0;
		}
	},
	ARCHERY(1, "Archery", Attributes.AGILITY, SkillGroup.NONE)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	AUTOMATICS(2, "Automatics", Attributes.AGILITY, SkillGroup.FIREARMS)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI ||
				role == Role.RIGGER)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	BLADES(3, "Blades", Attributes.AGILITY, SkillGroup.CLOSE_COMBAT)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	CLUBS(4, "Clubs", Attributes.AGILITY, SkillGroup.CLOSE_COMBAT)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	HEAVY_WEAPONS(5, "Heavy Weapons", Attributes.AGILITY, SkillGroup.NONE)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	LONGARMS(6, "Longarms", Attributes.AGILITY, SkillGroup.FIREARMS)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI ||
				role == Role.RIGGER)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	PISTOLS(7, "Pistols", Attributes.AGILITY, SkillGroup.FIREARMS)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI ||
				role == Role.SPELLCASTER)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	THROWING_WEAPONS(8, "Throwing Weapons", Attributes.AGILITY, SkillGroup.NONE)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	UNARMED_COMBAT(9, "Unarmed Combat", Attributes.AGILITY, SkillGroup.NONE)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	EXOTIC_MELEE_WEAPON(10, "Exotic Melee Weapon", Attributes.AGILITY, SkillGroup.NONE)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	EXOTIC_RANGED_WEAPON(11, "Exotic Ranged Weapon", Attributes.AGILITY, SkillGroup.NONE)	{
		public int getDef(Role role)	{
			if (role == Role.STREET_SAMURAI)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	GUNNERY(12, "Gunnery", Attributes.AGILITY, SkillGroup.NONE)	{
		public int getDef(Role role)	{
			if (role == Role.RIGGER)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	},
	PILOT_GROUND_CRAFT(13, "Pilot Ground Craft", Attributes.NONE, SkillGroup.NONE)	{
		public int getDef(Role role)	{
			if (role == Role.RIGGER)	{
				return getRndm();
			} else	{
				return 0;
			}
		}
	};

	public static final Map<Integer,Skills> map;
	private static final List<Integer> defValues = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
	private final int index;
	private final String name;
	private final Attributes attr;
	private final SkillGroup skillGroup;
	
	static	{
		map = new HashMap<Integer,Skills>();
		for (Skills skill : Skills.values())	{
			map.put(skill.index, skill);
		}
	}
	
	public abstract int getDef(Role role);

	public static Skills findByIndex(int index)	{
		return map.get(index);
	}
	
	Skills(int index, String name, Attributes attr, SkillGroup skillGroups)	{
		this.index = index;
		this.name = name;
		this.attr = attr;
		this.skillGroup = skillGroups;
	}
	
	public int getIndex()	{
		return index;
	}
	
	public Attributes getLnkdAttr()	{
		return attr;
	}
	
	public SkillGroup getSkillGroup()	{
		return skillGroup;
	}
	
	@Override
	public String toString()	{
		return name;
	}
	
	public static int[] getIndices()	{
		int[] indices = new int[Skills.values().length];
		int i = 0;
		for (Skills skill : Skills.values())	{
			indices[i]= skill.getIndex();
			i++;
		}
 		return indices;
	}
	
	private static int getRndm()	{
		Collections.shuffle(defValues);
		return defValues.get(0);
	}
}
