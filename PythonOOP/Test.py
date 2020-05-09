#!/usr/bin/python3
from Person import Person
from Fighter import Fighter
from Warrior import Warrior
from KnightErrant import KnightErrant

# NOTE: MUST BE RAN USING PYTHON 3+
print("\n" + "-"*30 + "CREATING AND TESTING PERSON FEATURES" + "-"*30)
sam = Person("Sam")  # creates default person (age 13, zero wealth)
kim = Person("Kim", 23, 12)  # creates person with age 23 and wealth 12
print(sam.__str__())  # prints default person sam
print(kim.__str__())  # prints person kim
print(sam.__eq__(kim))  # checks to see if two people are equal; returns FALSE


# Create Fighter Objects (order for skills is spear, combat, mace, sword)
print("\n"*4 + "-"*30 + "CREATING FIGHTERS/WARRIORS/KNIGHTS" + "-"*30)
# WARRIORS
ken = Warrior("Ken", 23, 760, 5, 3, 2, 12)  # broadsword lvl > 10, sets to 10
ray = Warrior("Ray", 45, 9560, 8, 7, 6, 6)
han = Warrior("Han", 55, 4300, 7, 4, 3, 6)
# KNIGHTS
wes = KnightErrant("Wes", 27, 1940, 2, 5, 4, 4)
leo = KnightErrant("Leo", 34, 5020, 10, 7, 4, 4)
dan = KnightErrant("Dan", 28, 1242, 4, -2, 5, 4)  # combat lvl < 0, sets to 0
# FIGHTERS
bob = Fighter("Bob", 17, 80, 4, 9, 4, 1)  # throws error bc age < 18
pat = Fighter("Pat", 18)  # creates fighter w skill lvls zero and wealth 1
joe = Fighter("Joe", 19, 95)  # creates fighter w default skill lvls (all zero)
tom = Fighter("Tom", 30, 3421, 6, 5, 4, 4)
cas = Fighter("Cas", 25, 1212, 6, 3, 7, 2)


print("\n"*5 + "-"*30 + "TESTING INVALID PARAMETERS" + "-"*30)
bob.challenge(joe, "mace")  # says you can't fight bc not a fighter
joe.challenge(ken, "")  # says invalid skill
joe.challenge(ken, "knife")  # says invalid skill
joe.challenge(joe, "spear")  # says fighter can't fight self


# NOTE: Skill str in these cases is also case-insensitive to avoid issues:
print("\n"*4+"-"*30+"TESTING FIGHTER/WARRIOR/KNIGHT FEATURES"+"-"*30)
print("\n"+"-"*15+"Withdrawing Before Having Fought (any) Battles:"+"-"*15)
tom.withdraw(ken, "spear")  # says it's invalid bc fighter hasn't fought before

print("\n"+"-"*15+"Challenging Different Fighters:"+"-"*15)
print("\nUP NEXT: FIGHTER VS FIGHTER\n")
# CASE WHERE ONE FIGHTER WINS AND OTHER LOSES (+1 skill randomly):
tom.challenge(joe, "MACE")  # fighter tom wins bc 4 > 0
print("\n")
# CASE WHERE WINNER IS RANDOMLY CHOSEN (+1 randomly given for selected skill):
cas.challenge(tom, "Spear")  # winner rand chosen bc 6 = 6
print("\nUP NEXT: FIGHTER VS WARRIOR\n")
# CASE WHERE FIGHTER WINS (+1 given with certainty for selected skill):
tom.challenge(ken, "spear")  # sends challenge request to warrior
ken.accept_first()  # accepts tom's challenge, fighter tom wins bc 6 > 5
print("\n")
# CASE WHERE WARRIOR WINS (+1 randomly given for selected skill):
joe.challenge(ken, "unarmed combat")  # sends challenge request to warrior
ken.accept_first()  # accepts joe's challenge, warrior ken wins bc 3 > 0
print("\n")
# CASE WHERE WINNER IS RANDOMLY CHOSEN (+1 randomly given for selected skill):
cas.challenge(ken, "unarmed combat")  # sends challenge request to warrior
ken.accept_first()  # accepts cas' challenge, winner rand chosen bc 3 = 3

print("\nUP NEXT: FIGHTER VS KNIGHT\n")
# CASE WHERE FIGHTER WINS (+2 given with certainty for selected skill):
cas.challenge(dan, "unarmed combat")  # sends challenge request to knight
dan.accept_first()  # accepts cas' challenge, fighter cas wins
print("\n")
# CASE WHERE KNIGHT WINS (+1 randomly given for selected skill):
joe.challenge(wes, "spear")  # sends challenge request to knight
wes.accept_first()  # accepts joe's challenge, knight wins bc 2 > 0
print("\n")
# CASE WHERE WINNER IS RANDOMLY CHOSEN (+1 randomly given for selected skill):
tom.challenge(wes, "broadsword")  # sends challenge request to knight
wes.accept_first()  # accepts tom's challenge, winner rand chosen bc 4 = 4

print("\nUP NEXT: WARRIOR VS WARRIOR\n")
# CASE WHERE ONE WARRIOR WINS AND OTHER LOSES (+1 skill randomly):
ray.challenge(han, "mace")  # sends challenge request to warrior
han.accept_first()  # accepts ray's challenge, ray wins bc 6 > 3
print("\n")
# CASE WHERE WINNER IS RANDOMLY CHOSEN (+1 randomly given for selected skill):
han.challenge(ray, "broadsword")  # sends challenge request to warrior
ray.accept_first()  # accepts han's challenge, winner rand chosen bc 6 = 6

print("\nUP NEXT: WARRIOR VS KNIGHT\n")
# CASE WHERE WARRIOR WINS (+1 given with certainty for selected skill):
ken.challenge(wes, "spear")  # sends challenge request to knight
wes.accept_first()  # accepts ken's challenge, warrior ken wins
print("\n")
# CASE WHERE KNIGHT WINS (+1 randomly given for selected skill):
ray.challenge(leo, "spear")  # sends challenge request to knight
leo.accept_first()  # accepts ray's challenge, knight leo wins bc 10 > 8
print("\n")
# CASE WHERE WINNER IS RANDOMLY CHOSEN (+1 randomly given for selected skill):
ray.challenge(leo, "unarmed combat")  # sends challenge request to knight
leo.accept_first()  # accepts ray's challenge, winner rand chosen bc 7 = 7

print("\nUP NEXT: KNIGHT VS KNIGHT\n")
# CASE WHERE ONE WARRIOR WINS AND OTHER LOSES (+1 skill randomly):
leo.challenge(dan, "spear")  # sends challenge request to knight
dan.accept_first()  # accepts leo's challenge, knight leo wins bc 10 > 4
print("\n")
# CASE WHERE WINNER IS RANDOMLY CHOSEN (+1 randomly given for selected skill):
dan.challenge(leo, "broadsword")  # sends challenge request to knight
leo.accept_first()  # accepts dan's challenge, winner rand chosen bc 4 = 4

print("\n"+"-"*15+"When Fighter gets to Zero Wealth:"+"-"*15)
# here pat starts with 1 wealth but loses so he ends up with zero wealth:
pat.challenge(tom, "broadsword")
print("\n")
# if pat tries to issue another challenge an error occurs
pat.challenge(tom, "mace")  # says cant fight with zero wealth
# error also occurs if pat is the recipient of a challenge
tom.challenge(pat, "broadsword")  # says cant fight with zero wealth
pat.wealth = -5  # if wealth is set to a neg value, it will be zero
print(pat)  # will print zero wealth

print("\n"+"-"*15+"Withdrawing After Having Fought (any) Battles:"+"-"*15)
leo.challenge(dan, "spear")  # sends challenge request to knight
leo.withdraw(dan, "spear")  # withdraws challenge request from dan's list
leo.withdraw(dan, "spear")  # says can't withdraw bc challenge doesn't exist
# next line gets ignored bc you can't withdraw from non-warrior's challenge:
# NOTE: In this case, non-warrior refers to warriors and knights ONLY
tom.withdraw(joe, "mace")  # fighter trying to withdraw from non-warrior

print("\n"+"-"*15+"Accepting Random Challenge Requests:"+"-"*15)
print("\nFOR WARRIORS:\n")
# add some challenges to a list so we can choose one randomly:
cas.challenge(ken, "spear")
tom.challenge(ken, "mace")
dan.challenge(ken, "broadsword")
print("\nWarrior's List Before Accepting Random Challenge:")
ken.print_list()
print("\n")
ken.accept_random()  # accepts random challenge from list of pending fights
print("\nWarrior's List After Accepting Random Challenge:")
ken.print_list()

print("\nFOR KNIGHTS:\n")
# add some challenges to a list so we can choose one randomly:
cas.challenge(wes, "mace")
tom.challenge(wes, "unarmed combat")
ray.challenge(wes, "spear")
print("\nKnight's List Before Accepting Random Challenge:")
wes.print_list()
print("\n")
wes.accept_random()  # accepts random challenge from list of pending fights
print("\nKnight's List After Accepting Random Challenge:")
wes.print_list()

print("\n"+"-"*15+"Declining Challenge Requests:"+"-"*15)
print("\nFOR WARRIORS:\n")
# add some challenges to a list so we can choose to decline:
han.challenge(ken, "mace")
print("\nWarrior's List Before Declining First Challenge:")
ken.print_list()
print("\nDecline First On List:")
ken.decline_first()  # will decline first on list
print("\nWarrior's List Before Declining Random Challenge:")
ken.print_list()
print("\nDecline Random On List:")
ken.decline_random()  # will decline random on list
print("\nWarrior's List After Declining Random Challenge:")
ken.print_list()

print("\nFOR KNIGHTS:\n")
# add some challenges to a list so we can choose to decline:
han.challenge(wes, "broadsword")
print("\nKnight's List Before Declining First Challenge:")
wes.print_list()
print("\nDecline First On List:")
wes.decline_first()  # will decline first on list
print("\nKnight's List Before Declining Random Challenge:")
wes.print_list()
print("\nDecline Random On List:")
wes.decline_random()  # will decline random on list
print("\nKnight's List After Declining Random Challenge:")
wes.print_list()

print("\n"+"-"*15+"Testing Knight's Traveling Features:"+"-"*15)
dan.travel()  # says dan's is traveling
tom.challenge(dan, "spear")  # sends challenge request to knight
dan.accept_first()  # says cannot accept challenge while traveling
dan.decline_random()  # says cannot decline challenge while traveling
dan.withdraw(ray, "unarmed combat")  # says cannot withdraw while traveling
dan.challenge(tom, "mace")  # says cannot challenge others while traveling
dan.return_from_travel()  # knight ends journey and collects treasure (if any)
print(dan)  # will show changes to dan's wealth if treasure was found
print("\nKnight Can Fight After Returning from Travel:")
dan.accept_first()  # accepts first challenge on list

print("\n"+"-"*15+"Spamming Challenge Requests:"+"-"*15)
print("\nFOR WARRIORS:\n")
# add some challenges to list
tom.challenge(ray, "spear")
tom.challenge(ray, "mace")
tom.challenge(ray, "broadsword")
tom.challenge(ray, "mace")  # doesn't get added to list bc already sent
tom.challenge(ray, "unarmed combat")
tom.challenge(ray, "spear")  # doesn't get added to list bc already sent
print("\n")
ray.print_list()
print("\nFOR KNIGHTS:\n")
ken.challenge(leo, "spear")
ken.challenge(leo, "mace")
ken.challenge(leo, "unarmed combat")
ken.challenge(leo, "spear")  # doesn't get added to list bc already sent
ken.challenge(leo, "broadsword")
ken.challenge(leo, "spear")  # doesn't get added to list bc already sent
print("\n")
leo.print_list()
