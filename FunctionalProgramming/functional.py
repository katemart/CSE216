from functools import reduce


def flatten(input_list: list) -> list:
    """
    flattens a list (possibly containing nested lists)
    :param input_list: list to be flattened
    :return: flattened list (straight list w/ no nested lists within). If empty
    list is given, an empty list is returned
    """
    if not input_list:
        return input_list
    if isinstance(input_list[0], list):
        return flatten(input_list[0]) + flatten(input_list[1:])
    return input_list[:1] + flatten(input_list[1:])


def reverse(input_list: list) -> list:
    """
    reverses a list's order (possibly containing nested lists)
    :param input_list: list to be reversed
    :return: list in reverse order (if passed list contains nested lists then
    those will be kept but their contents will be reversed as well). If empty
    list is given, an empty list is returned
    """
    if not input_list:
        return input_list
    if not isinstance(input_list[0], list):
        return reverse(input_list[1:]) + [input_list[0]]
    else:
        return reverse(input_list[1:]) + [reverse(input_list[0])]


def compress(input_list) -> list:
    """
    removes consecutive duplicates from a list
    :param input_list: list where duplicates are to be removed from
    :return: list w/o consecutive duplicates. If empty list is given,
    an empty list is returned
    """
    if not input_list:
        return input_list
    if len(input_list) == 1:
        return input_list
    if input_list[0] == input_list[1]:
        return compress(input_list[1:])
    else:
        return input_list[:1] + compress(input_list[1:])


def capitalized(items: list) -> list:
    """
    finds all words that start with a capital letter in a given list
    :param items: list where capitalized words are to be found from
    :return: list containing words that start with a capital letter (ONLY)
    """
    return list(filter(lambda x: len(x) > 0 and x[0].isupper(), items))


def longest(strings: list, from_start=True) -> object:
    """
    finds the longest object in a list
    :param strings: list where longest object is to be found from
    :param from_start: boolean to decide which object is to be returned if more
    than one object in the given list has same length. If True, object
    encountered earlier in iteration is returned. If False, object  encountered
    later is returned.
    :return: longest object from given list.
    NOTE: identity value is [] (i.e., if an empty list is given, an empty list
    is returned
    """
    return reduce((lambda x, y:
                   ((y, x)[len(x) > len(y)], (y, x)[from_start])
                   [len(x) == len(y)]), strings, [])


def composition(f, g):
    """
    takes function f and function g and composes them into one function g(f(x))
    :param f: first function to be composed (gets called first)
    :param g: second function to be composed (gets called second)
    :return: composition of first and second function
    """
    return lambda x: g(f(x))


def n_composition(*functions):
    """
    takes n number of function(s) and composes them into one function
    :param functions: function(s) to be composed
    :return: composition of n functions
    NOTE: Identity value is x (i.e., if no functions are given the value is
    returned
    """
    return reduce((lambda f, g: lambda x: g(f(x))), functions, lambda x: x)
