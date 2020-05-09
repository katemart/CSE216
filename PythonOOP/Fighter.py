from __future__ import annotations
import random
from Person import Person


class Fighter(Person):
    """
    This is a class for creating a Fighter and its features and for such
    Fighter to engage in fights with other Fighters, Warriors, and/or Knights.
    """
    def __init__(self, name: str, age: int, wealth: int = 1,
                 spear_lvl: int = 0, combat_lvl: int = 0, mace_lvl: int = 0,
                 broadsword_lvl: int = 0) -> None:
        """
        Constructor for Fighter class. Creates Fighter with given parameters
        ONLY if person is adult, else an error is printed. Default creation is
        name, 1 wealth, and 0 for each skill level.
        :param name: The Fighter's name
        :param age: The Fighter's age
        :param wealth: The Fighter's (initial) wealth (default value is 1)
        :param spear_lvl: The Fighter's spear (initial) skill level
        :param combat_lvl: The Fighter's unarmed combat (initial) skill level
        :param mace_lvl: The Fighter's mace (initial) skill level
        :param broadsword_lvl: The Fighter's broadsword (initial) skill level
        :return: None
        """
        try:
            self.age = age
            if self.is_adult():
                Person.__init__(self, name, age, wealth)
                self.__set_skills(spear_lvl, combat_lvl, mace_lvl,
                                  broadsword_lvl)
                self.__has_fought = False  # var to check if fighter has fought
            else:
                raise ValueError("Invalid age.")
        except ValueError as e:
            print(str(e) + " You must be an adult to be a fighter!")

    def challenge(self, opponent: Fighter, skill: str) -> None:
        """
        Simulates fight scenario with the given skill. First checks that all
        initial rules are met and if so depending on the type of opponent, the
        fight will begin instantly or a request for a challenge will be sent.
        Any distribution of wealth/skill points will occur after a fight has
        taken place.
        :param opponent: Fighter to be fought in battle
        :param skill: skill to be fought with
        :return: None
        """
        from Warrior import Warrior
        from KnightErrant import KnightErrant
        skill = skill.strip().casefold()
        # check if Fighter object exists and if fight ok to begin challenge:
        if not hasattr(self, 'name') or not hasattr(opponent, 'name'):
            print("You can not fight. You are not a fighter.")
            return
        elif not self.__start_check(self, opponent, skill):
            return
        # if ok, check opponent instance and call battle_warrior():
        if isinstance(opponent, KnightErrant):
            self.battle_warrior(opponent, skill)
        elif isinstance(opponent, Warrior):
            self.battle_warrior(opponent, skill)
        else:
            print("%s %s is now fighting %s %s using %s" %
                  (self.__class__.__name__, self.name,
                   opponent.__class__.__name__, opponent.name, skill))
            self.__battle_helper(self, opponent, skill)
        return None

    def battle_warrior(self, opponent: Fighter, skill: str) -> None:
        """
        Takes care of sending requests to warriors/knights. It also informs
        the user if a request has been accepted (in which case a fight between
        two opponents will take place), declined, or is still pending by a
        warrior/knight.
        :param opponent: Fighter to be fought in battle
        :param skill: skill to be fought with
        :return: None
        """
        from Warrior import ChallengeData
        challenge_list = opponent.challenges_received
        fighter_in_list = False
        for challenge in challenge_list:
            if challenge.fighter == self and challenge.skill == skill:
                fighter_in_list = True
                if challenge.answer == None:
                    print("Request already sent. %s has not responded to "
                          "%s's %s challenge yet." % (opponent.name,
                                                      self.name, skill))
                    return
                elif challenge.answer == False:
                    print("%s has declined %s's %s challenge." % (
                        opponent.name, self.name, skill))
                    challenge_list.remove(challenge)
                    return
                elif challenge.answer == True:
                    print("%s has accepted %s's fight offer. "
                          "Initiating fight with %s." % (opponent.name,
                                                         self.name, skill))
                    print("%s %s is now fighting %s %s using %s" %
                          (self.__class__.__name__, self.name,
                           opponent.__class__.__name__, opponent.name, skill))
                    self.__battle_helper(self, opponent, skill)
                    challenge_list.remove(challenge)
                break
        if not fighter_in_list:
            print("Sending challenge request [%s vs %s] with skill [%s]" %
                  (self.name, opponent.name, skill))
            challenge_list.append(ChallengeData(self, skill))
            return

    def withdraw(self, opponent: Fighter, skill: str) -> None:
        """
        Allows Fighter to withdraw a particular challenge that has been
        previously sent to an opponent ONLY if fighter that is trying to
        withdraw has participated in at least one battle before, otherwise
        fighter cannot withdraw challenge.
        :param opponent: Fighter that challenge will be withdrawn from
        :param skill: specific skill of challenge to be withdrawn from
        :return: None
        """
        from Warrior import Warrior
        if self.__has_fought == True:
            if isinstance(opponent, Warrior):
                skill = skill.strip().casefold()
                opponent.remove_from_challenges(self, skill)
        else:
            print("Cannot withdraw from a challenge without having fought at "
                  "least one battle!")

    @staticmethod
    def __battle_helper(challenger: Fighter, opponent: Fighter,
                        skill: str) -> None:
        """
        Serves as a "bridge" between Fighter and Fight classes and determines
        who the winner/loser from a fight is. This method should not be
        accessed outside of this class.
        :param challenger: Fighter involved in battle
        :param opponent: Fighter involved in battle
        :param skill: skill to be fought with
        :return: None
        """
        from Fight import Fight
        challenger_skill = challenger.__skills.get(skill)
        opponent_skill = opponent.__skills.get(skill)
        fight = Fight(challenger, opponent, challenger_skill, opponent_skill)
        fight.fight()
        challenger.__has_fought = True
        opponent.__has_fought = True
        Fighter.__prizes(skill, fight.winner, fight.loser)
        print(fight.winner)
        print(fight.loser)

    @staticmethod
    def __prizes(skill: str, winner: Fighter, loser: Fighter) -> None:
        """
        Distributes wealth and skill changes to winner/loser from a fight. This
        method should not be accessed outside of this class.
        :param skill: skill whose levels are to be changed
        :param winner: winner from fight
        :param loser: loser from fight
        :return: None
        """
        from Warrior import Warrior
        from KnightErrant import KnightErrant
        if type(winner) == Fighter and type(loser) == Warrior:
            wealth_diff = 25
            skill_gained = 1
            winner.wealth += wealth_diff
            loser.wealth -= wealth_diff
            lvl = winner.__skills[skill] + skill_gained
            print("%s has won the fight! %s has gained %d wealth and +%d "
                  "skill level for %s.\n%s has lost %d wealth and gained no "
                  "additional skill." % (winner.name, winner.name, wealth_diff,
                                         skill_gained, skill, loser.name,
                                         wealth_diff))
            winner.__skills[skill] = winner.__check_lvl(skill, lvl)
        elif (type(winner) == Fighter and
              type(loser) == KnightErrant):
            wealth_diff = 40
            skill_gained = 2
            winner.wealth += wealth_diff
            loser.wealth -= wealth_diff
            lvl = winner.__skills[skill] + skill_gained
            print("%s has won the fight! %s has gained %d wealth and +%d "
                  "skill level for %s.\n%s has lost %d wealth and gained no "
                  "additional skill." % (winner.name, winner.name, wealth_diff,
                                         skill_gained, skill, loser.name,
                                         wealth_diff))
            winner.__skills[skill] = winner.__check_lvl(skill, lvl)
        elif (type(winner) == Warrior and
              type(loser) == KnightErrant):
            wealth_diff = 20
            skill_gained = 1
            winner.wealth += wealth_diff
            loser.wealth -= wealth_diff
            lvl = winner.__skills[skill] + skill_gained
            print("%s has won the fight! %s has gained %d wealth and +%d "
                  "skill level for %s.\n%s has lost %d wealth and gained no "
                  "additional skill." % (winner.name, winner.name, wealth_diff,
                                         skill_gained, skill, loser.name,
                                         wealth_diff))
            winner.__skills[skill] = winner.__check_lvl(skill, lvl)
        else:
            wealth_diff = 10
            skill_gained = 1
            winner.wealth += wealth_diff
            loser.wealth -= wealth_diff
            champ = random.choice([winner, loser])
            lvl = champ.__skills[skill] + skill_gained
            print("%s has won the fight! %s has gained %d wealth and %s has "
                  "lost %d wealth.\n%s has obtained +%d skill level for %s "
                  "through random selection." % (winner.name, winner.name,
                                                 wealth_diff, loser.name,
                                                 wealth_diff,champ.name,
                                                 skill_gained, skill))
            champ.__skills[skill] = champ.__check_lvl(skill, lvl)

    def __set_skills(self, spear_lvl: int, combat_lvl: int, mace_lvl: int,
                     broadsword_lvl: int) -> None:
        """
        Assigns given skill levels to corresponding skills in a dictionary.
        This method should not be accessed outside of this class.
        :param spear_lvl: level for spear skill to be set to
        :param combat_lvl: level for unarmed combat skill to be set to
        :param mace_lvl: level for mace skill to be set to
        :param broadsword_lvl: level for broadsword skill to be set to
        :return: None
        """
        self.__skills = {
            "spear": self.__check_lvl("spear", spear_lvl),
            "unarmed combat": self.__check_lvl("unarmed combat", combat_lvl),
            "mace": self.__check_lvl("mace", mace_lvl),
            "broadsword": self.__check_lvl("broadsword", broadsword_lvl)
        }

    @staticmethod
    def __start_check(challenger: Fighter, opponent: Fighter,
                      skill: str) -> bool:
        """
        Checks if a fight is ok to start (i.e., given skill is valid, both
        fighters' wealth is not zero and challenger is not trying to fight
        self). Returns true if fight is ok to begin fight or False if it isn't.
        This method should not be accessed outside of this class.
        :param challenger: Fighter to be compared against opponent
        :param opponent: Fighter to be compared against challenger
        :param skill: skill to be checked if valid
        :return: boolean True or False
        """
        if skill in challenger.__skills and skill in opponent.__skills:
            if not challenger.__eq__(opponent):
                if challenger.wealth > 0 and opponent.wealth > 0:
                    return True
                else:
                    print("Cannot fight with zero wealth")
                    return False
            else:
                print("%s %s cannot fight self" %
                      (challenger.__class__.__name__, challenger.name))
                return False
        else:
            print("That is not a valid skill")
            return False

    def __check_lvl(self, skill: str, level: int) -> int:
        """
        Ensures that given skill level for a given skill is not < 0 or > 10.
        This method should not be accessed outside of this class.
        :param skill: The skill to adjust level of
        :param level: The number the skill level should be set to
        :return: int level
        """
        if level < 0:
            print(self.name + "\'s " + skill + " level cannot be less than "
                                               "zero, setting level to zero")
            level = 0
        elif level > 10:
            print(self.name + "\'s " + skill + " level cannot exceed 10, "
                                               "setting to level to 10")
            level = 10
        return level

    # str() for the Fighter class
    def __str__(self) -> str:
        return super().__str__() + "\tSkills:%s" % self.__skills

    # methods to check if two fighters are equal
    def __eq__(self, other) -> bool:
        if not super().__eq__(other):
            return False
        elif not isinstance(other, Fighter):
            return False
        return super().__eq__(other) and (self.__skills == other.__skills)

    def __ne__(self, other) -> bool:
        return not self.__eq__(other)
