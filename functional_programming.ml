(* takes two integers x and n and returns integer x^n *)
let rec pow x n = match n with
  | 0 -> 1
  | n when n > 0 -> x * pow x (n-1)
  | n -> 0;;

(* takes float x and integer n and returns float x^n *)
let rec float_pow x n = match n with
  | 0 -> 1.0
  | n when n > 0 -> x *. float_pow x (n-1)
  | n -> 1.0/.(x *. float_pow x ((-n)-1));;

(* takes in a list lst and returns a list with the reversed order *)
let reverse lst =
  let rec rev_helper acc lst = match lst with
    | [] -> acc
    | head::tail -> rev_helper (head::acc) tail in rev_helper [] lst;;

(* takes in a list lst and returns a list with consecutive duplicates removed *)
let rec compress lst = match lst with
  | [] -> lst
  | [_] -> lst
  | head::(next::_ as tail) when head = next -> compress tail
  | head::tail -> head::compress tail;;

(**
 * takes in a list lst and returns a list with any consecutive duplicates
 * put together into nested lists
*)
let cluster lst =
  let rec c_helper temp acc lst = match lst with
    | [] -> [[]]
    | [x] -> (x::temp)::acc
    | head::(next::_ as tail) when head = next ->
      c_helper (head::temp) acc tail
    | head::tail -> c_helper [] ((head::temp)::acc) tail in
  c_helper [] [] (reverse lst);;

(**
 * takes in a list lst and integers i and j and returns a sublist from index i
 * (inclusive) to j (exclusive)
*)
let rec slice lst i j = match lst with
  | [] -> lst
  | head::tail when j = 0 -> []
  | head::tail when i = 0 -> head::slice tail 0 (j-1)
  | head::tail -> slice tail (i-1) (j-1);;

(* takes in two functions f and g and returns their composition f (g x) *)
let composition f g x = f (g x);;

(**
 * takes in functions f and g and list lst and returns true if functions f and g
 * are equal on each element of lst, otherwise returns false
*)
let rec equiv_on f g lst = match lst with
  | [] -> true
  | head::tail when f head = g head -> equiv_on f g tail
  | _ -> false;;

(**
 * takes in function cmp and list lst and returns a list with cmp applied to
 * two elements from lst at a time. If lst has an odd size, cmp isn't applied
 * to last element, so element is returned within list "as is"
*)
let rec pairwisefilter cmp lst = match lst with
  | [] -> lst
  | [_] -> lst
  | head::next::tail -> (cmp head next)::pairwisefilter cmp tail;;

(**
 * takes in list of tuples tup_lst and an integer value and returns the integer
 * result corresponding to the polynomial function based on tup_lst and value
 * given
*)
let rec polynomial tup_lst value = match tup_lst with
  | [] -> 0
  | (c, e)::tail -> c * (pow value e) + polynomial tail value;;

(* defines boolean expressions to be used in truth_table function *)
type bool_expr =
  | Lit of string
  | Not of bool_expr
  | And of bool_expr * bool_expr
  | Or of bool_expr * bool_expr;;

(**
 * takes in two values lit1 lit2 and their logical expression expr and returns
 * a list of the truth values for each as a tuple of three elements
*)
let truth_table lit1 lit2 expr =
  let rec tt_helper lit1_val lit2_val expr = match expr with
    | Lit x when x = lit1 -> lit1_val
    | Lit x -> lit2_val
    | Not expr ->
      not(tt_helper lit1_val lit2_val expr)
    | And(expr1, expr2) ->
      (tt_helper lit1_val lit2_val expr1) &&
      (tt_helper lit1_val lit2_val expr2)
    | Or(expr1, expr2) ->
      (tt_helper lit1_val lit2_val expr1) ||
      (tt_helper lit1_val lit2_val expr2) in
  [(true, true, tt_helper true true expr);
   (true, false, tt_helper true false expr);
   (false, true, tt_helper false true expr);
   (false, false, tt_helper false false expr)];;

(* defines a polymorphic binary_tree to be used in tree2str function *)
type 't binary_tree =
  | Empty
  | Node of 't * 't binary_tree * 't binary_tree;;

(* takes in a tree and returns its structure as a string representation *)
let rec tree2str tree = match tree with
  | Empty -> ""
  | Node(value, Empty, Empty) -> string_of_int(value)
  | Node(value, left, right) ->
    string_of_int(value)^"("^tree2str left^","^tree2str right^")";;
