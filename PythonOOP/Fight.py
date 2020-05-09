from __future__ import annotations
import random
from Fighter import Fighter


class Fight:
    """
    This is a class for comparing two Fighters by the skill that is passed,
    it determines and returns a winner/loser.
    """
    def __init__(self, fighter1: Fighter, fighter2: Fighter, skill1: int,
                 skill2: int) -> None:
        """
        Constructor for the Fight class. Creates Fight object with the
        specified parameters.
        :param fighter1: Fighter to be compared against fighter2
        :param fighter2: Fighter to be compared against fighter1
        :param skill1: skill from fighter 1 to be used in comparison
        :param skill2: skill from fighter 2 to be used in comparison
        """
        self.fighter1 = fighter1
        self.fighter2 = fighter2
        self.skill1 = skill1
        self.skill2 = skill2
        self.winner = None
        self.loser = None

    def fight(self) -> None:
        """
        Method compares fighter1 and fighter2 by skill level of skill passed to
        see who wins/loses. If the skill levels between the two fighters are
        the same, the winner will be chosen randomly.
        :return: None
        """
        if self.skill1 > self.skill2:
            self.outcome(self.fighter1, self.fighter2)
        elif self.skill1 < self.skill2:
            self.outcome(self.fighter2, self.fighter1)
        else:
            fighters = [self.fighter1, self.fighter2]
            champion = random.choice(fighters)
            if champion == self.fighter1:
                self.outcome(self.fighter1, self.fighter2)
            else:
                self.outcome(self.fighter2, self.fighter1)

    def outcome(self, winner: Fighter, loser: Fighter) -> None:
        """
        Method declares a winner/loser
        :param winner: fighter who won battle (comparison)
        :param loser: fighter who lost battle (comparison)
        :return:
        """
        self.winner = winner
        self.loser = loser

    def winner(self) -> Fighter:
        """
        Method returns winner from battle
        :return: Fighter who wins
        """
        return self.winner

    def loser(self) -> Fighter:
        """
        Method returns loser from battle
        :return: Fighter who loses
        """
        return self.loser
