from __future__ import annotations


class Person:
    """
    This is a class for creating a Person, which serves as the "base" class
    for all other classes (Fighters, Warriors, and Knights)
    """
    def __init__(self, name: str, age: int = 13, wealth: int = 0) -> None:
        """
        Constructor for Person class. Creates Person with given parameters,
        otherwise default creation is given name, 13 years old, and 0 wealth
        :param name: The Person's name
        :param age: The Person's age (default value is 13)
        :param wealth: The Person's wealth (optional, default value is zero)
        """
        self.__name = name
        self.__age = age
        self.__wealth = wealth
        self.__adult = self.is_adult()

    def is_adult(self) -> bool:
        """
        Method checks to see if the Person's age is >= 18. If yes returns
        True, otherwise returns False
        :return: bool
        """
        if self.age >= 18:
            return True
        else:
            return False

    # properties and setters for the Person class
    @property
    def name(self) -> str:
        return self.__name

    @name.setter
    def name(self, name: str) -> None:
        self.__name = name

    @property
    def age(self) -> int:
        return self.__age

    @age.setter
    def age(self, age: int) -> None:
        self.__age = age
        self.is_adult()

    @property
    def wealth(self) -> int:
        return self.__wealth

    @wealth.setter
    def wealth(self, wealth: int) -> None:
        """
        Method sets Person's wealth attribute; won't allow wealth to be neg val
        :param wealth: The (int) value a Person's wealth should be set to
        :return: None
        """
        if wealth < 0:
            self.__wealth = 0
        else:
            self.__wealth = wealth

    @property
    def adult(self) -> bool:
        return self.__adult

    # str() for the Person class
    def __str__(self) -> str:
        return "Name:%s\tAge:%d\tWealth:%d" % (self.name, self.age,
                                               self.wealth)

    # method to check if two persons are equal
    def __eq__(self, other) -> bool:
        if not isinstance(other, Person):
            return False
        return (self.__name, self.__age) == (other.__name, other.__age)

    def __ne__(self, other) -> bool:
        return not self.__eq__(other)
