import random
from Fighter import Fighter
from Warrior import Warrior


class KnightErrant(Warrior):
    """
    This is a class for creating a Knight and for such Knight to engage in
    fights with other Fighters, Warriors, and/or Knights.
    """
    def __init__(self, name: str, age: int, wealth: int, spear_lvl: int = 0,
                 combat_lvl: int = 0, mace_lvl: int = 0,
                 broadsword_lvl: int = 0) -> None:
        """
        Constructor for the Knight class. Creates Knight object, which inherits
        all allowed methods and features from Warrior.
        :param name: The Knight's name
        :param age: The Knight's age
        :param wealth: The Knight's (initial) wealth
        :param spear_lvl: The Knight's spear (initial) skill level
        :param combat_lvl: The Knight's unarmed combat (initial) skill level
        :param mace_lvl: The Knight's mace (initial) skill level
        :param broadsword_lvl: The Knight's broadsword (initial) skill level
        :return: None
        """
        Warrior.__init__(self, name, age, wealth, spear_lvl, combat_lvl,
                         mace_lvl, broadsword_lvl)
        self.__challenges_received = []  # list to keep track of pending fights
        self.is_traveling = False  # variable to check if knight is traveling

    def challenge(self, opponent: Fighter, skill: str) -> None:
        """
        Challenges a Fighter ONLY if the Knight initiating the challenge is not
        traveling, else an error is printed and challenge is not issued.
        :param opponent: Fighter to be fought in battle
        :param skill: skill to be fought with
        :return: None
        """
        if not self.is_traveling:
            super().challenge(opponent, skill)
        else:
            print("%s cannot challenge others while traveling." % self.name)

    def accept_first(self) -> None:
        """
        Accepts first challenge from a Knight's list of pending challenges ONLY
        if knight is not traveling, else an error is printed and challenge is
        not accepted.
        :return: None
        """
        if not self.is_traveling:
            super().accept_first()
        else:
            print("%s cannot accept challenges while traveling." % self.name)

    def decline_first(self) -> None:
        """
        Declines first challenge from a Knight's list of pending challenges
        ONLY if knight is not traveling, else an error is printed and challenge
        is not declined.
        :return: None
        """
        if not self.is_traveling:
            super().decline_first()
        else:
            print("%s cannot decline challenges while traveling." % self.name)

    def accept_random(self) -> None:
        """
        Accepts RANDOM challenge from a Knight's list of pending challenges
        ONLY if knight is not traveling, else an error is printed and challenge
        is not accepted.
        :return: None
        """
        if not self.is_traveling:
            super().accept_random()
        else:
            print("%s cannot accept challenges while traveling." % self.name)

    def decline_random(self) -> None:
        """
        Declines RANDOM challenge from a Knight's list of pending challenges
        ONLY if knight is not traveling, else an error is printed and challenge
        is not declined.
        :return: None
        """
        if not self.is_traveling:
            super().decline_random()
        else:
            print("%s cannot decline challenges while traveling." % self.name)

    def withdraw(self, opponent: Fighter, skill: str) -> None:
        """
        Allows Knight to withdraw a particular challenge that has been
        previously sent to an opponent ONLY if knight is not traveling, else
        an error is printed and challenge is not withdrawn.
        :param opponent: Fighter that challenge will be withdrawn from
        :param skill: specific skill of challenge to be withdrawn from
        :return: None
        """
        if not self.is_traveling:
            super().withdraw(opponent, skill)
        else:
            print("%s cannot withdraw from challenges while traveling." %
                  self.name)

    def travel(self) -> None:
        """
        Marks the beginning of a knight's journey (i.e., whether the knight
        is traveling), and informs user that knight is traveling.
        :return: None
        """
        self.is_traveling = True
        print(self.name + " is traveling")

    def return_from_travel(self) -> None:
        """
        Marks the end of a knight's journey (i.e., the knight is no longer
        traveling), and informs the user that the knight has returned from
        travel. This method also adds wealth found from treasures during
        travels (if any) to the knight's current wealth.
        :return: None
        """
        self.is_traveling = False
        print(self.name + " has returned from travel")
        self.wealth += self.__find_treasure()

    def __find_treasure(self) -> int:
        """
        Randomly decides if a knight finds a treasure, if a treasure is
        found it then randomly decides the amount of the treasure (from
        value of 1-100). Returns amount found (if any), otherwise returns zero.
        This method should not be accessed outside of this class.
        :return: int value of treasure
        """
        found = random.choice([0,1])
        if found == 1:
            treasure = random.randint(1, 100)
            print("%s has found %d wealth while traveling" % (self.name,
                                                              treasure))
            return treasure
        else:
            return 0

    # str() for the Warrior class
    def __str__(self) -> str:
        return super().__str__()

    # methods to check if two knights are equal
    def __eq__(self, other) -> bool:
        if not super().__eq__(other):
            return False
        elif not isinstance(other, KnightErrant):
            return False
        return super().__eq__(other) and (self.__challenges_received,
                                          self.is_traveling ==
                                          other.__challenges_received,
                                          other.is_traveling)

    def __ne__(self, other) -> bool:
        return not self.__eq__(other)
