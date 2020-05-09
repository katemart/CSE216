import random
from Fighter import Fighter


class Warrior(Fighter):
    """
    This is a class for creating a Warrior and its features and for such
    Warrior to engage in fights with other Fighters, Warriors, and/or Knights.
    """
    def __init__(self, name: str, age: int, wealth: int, spear_lvl: int = 0,
                 combat_lvl: int = 0, mace_lvl: int = 0,
                 broadsword_lvl: int = 0) -> None:
        """
        Constructor for Warrior class. Creates Warrior object which inherits
        all allowed methods and features from Fighter.
        :param name: The Warrior's name
        :param age: The Warrior's age
        :param wealth: The Warrior's (initial) wealth
        :param spear_lvl: The Warrior's spear (initial) skill level
        :param combat_lvl: The Warrior's unarmed combat (initial) skill level
        :param mace_lvl: The Warrior's mace (initial) skill level
        :param broadsword_lvl: The Warrior's broadsword (initial) skill level
        :return: None
        """
        Fighter.__init__(self, name, age, wealth, spear_lvl, combat_lvl,
                         mace_lvl, broadsword_lvl)
        self.__challenges_received = []  # list to keep track of pending fights

    def accept_first(self) -> None:
        """
        Accepts first challenge from a Warrior's list of pending challenges.
        :return: None
        """
        if self.wealth > 0:
            challenger = self.challenges_received[0]
            challenger.answer = True
            challenger.fighter.challenge(self, challenger.skill)

    def decline_first(self) -> None:
        """
        Declines first challenge from a Warrior's list of pending challenges.
        :return: None
        """
        if self.wealth > 0:
            challenger = self.challenges_received[0]
            challenger.answer = False
            challenger.fighter.challenge(self, challenger.skill)

    def accept_random(self) -> None:
        """
        Accepts a RANDOM challenge from a Warrior's list of pending challenges.
        :return: None
        """
        if self.wealth > 0:
            challenge = random.choice(self.challenges_received)
            challenge.answer = True
            challenge.fighter.challenge(self, challenge.skill)

    def decline_random(self) -> None:
        """
        Declines a RANDOM challenge from a Warrior's list of pending
        challenges.
        :return: None
        """
        if self.wealth > 0:
            challenge = random.choice(self.challenges_received)
            challenge.answer = False
            challenge.fighter.challenge(self, challenge.skill)

    def remove_from_challenges(self, opponent: Fighter, skill: str) -> None:
        """
        Removes a challenge from a Warrior's list of pending challenges.
        :param opponent: Fighter to be removed from list
        :param skill: specific skill of challenge to be removed
        :return: None
        """
        skill = skill.strip().casefold()
        for challenge in self.__challenges_received:
            if challenge.fighter == opponent and challenge.skill == skill:
                self.__challenges_received.remove(challenge)
                print("%s's %s challenge request withdrawn from %s's list" %
                      (opponent.name, skill, self.name))
                return
        print("Cannot withdraw. No such challenge request exists.")

    def print_list(self) -> None:
        """
        Prints a Warrior's list of pending challenges by Fighter's name and
        Fighter's challenge skill.
        :return: None
        """
        print(str(self.name) + "\'s Pending Challenges List:")
        for t in self.challenges_received:
            print(type(t.fighter).__name__ + " Name: %s\tSkill: %s" %
                  (t.fighter.name, t.skill))

    # properties and setters for the Warrior class
    @property
    def challenges_received(self) -> list:
        return self.__challenges_received

    # str() for the Warrior class
    def __str__(self) -> str:
        return super().__str__()

    # methods to check if two warriors are equal
    def __eq__(self, other) -> bool:
        if not super().__eq__(other):
            return False
        elif not isinstance(other, Warrior):
            return False
        return super().__eq__(other) and (self.__challenges_received ==
                                          other.__challenges_received)

    def __ne__(self, other) -> bool:
        return not self.__eq__(other)


class ChallengeData:
    """
    This is a class used in lieu of a built-in Python data structure for
    creating a (combination of) Fighter, skill, and bool object to be added to
    a Warrior/Knight's list of pending challenges.
    """
    def __init__(self, fighter: Fighter, skill: str, answer: bool = None) \
            -> None:
        """
        Constructor for ChallengeData class. Creates a ChallengeData object,
        which is used within a Warrior/Knight's list of pending challenges.
        :param fighter: Fighter (challenger) to be added to list
        :param skill: skill of challenge request
        :param answer: bool value to indicate whether Fighter's a challenge
        has been accepted/denied/still pending by a Warrior/Knight
        :return: None
        """
        self.fighter = fighter
        self.skill = skill
        self.answer = answer

    # str() for the ChallengeData class
    def __str__(self) -> str:
        return super().__str__()
