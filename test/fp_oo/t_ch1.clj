(ns fp_oo.t-ch1
  (:use midje.sweet)
  (:use [fp_oo.ch1]))

(facts "about `second`"
  (fact "it returns the second element of a list"
    (second '(1 2 3)) => 2))

(fact "third"
      (third-nth '(1 2 3)) => 3
      (third-rest '(1 2 3)) => 3)

(fact "add-squares"
      (add-squares 1 2 5) => 30)

(fact "range-fac"
      (range-fac 5) => 120)

(fact "first-5"
      (first-5 [1 2 3 4 5 6 7]) => [1 2 3 4 5])

(fact "count-duplicates"
      (count-duplicates [ 1 2 2 3 3 3 4 4 4 4]) => 6)

(fact 
  "odds-and-evens creates a seq with the odds from the first and evens "
  "from the second"

      (odds-and-evens [1 2 3 4] [5 6 7 8]) => [1 3 6 8])

(fact "repeat-range creates a seq of seqs with count matching item"
      (repeat-range 4) => '((1) (2 2) (3 3 3) (4 4 4 4)))

(fact "star-struck intersperses a seq with stars"
      (star-struck [1 2 3 4 5]) => '[1 * 2 * 3 * 4 * 5 *])

(fact "chop-shop creates a seq comprised the first half of the first"
      "and the second half of the second"
      (chop-shop [1 2 3 4] [5 6 7 8]) => [1 2 7 8])

(fact "ranges"
      (ranges [4 2 3]) => [4 4 4 4 2 2 3 3 3])

(fact "pairs"
      (pairs [1 2 3 4 5 6 7 8]) => [[1 2] [3 4] [5 6] [7 8]])

(fact "only-odd-numbers?"
      (only-odd-numbers? [ 1 3 5 7]) => true
      (only-odd-numbers? [ 1 2 3 4]) => false)

(fact "strip-prefixed"
      (strip-prefixed "foo" ["foobar" "barbar" "foobaz" "bazquux" "foo"])
      => ["barbar" "bazquux"])

(fact "prefix-of?"
      (prefix-of? [1 2] [1 2 3 4]) => true
      (prefix-of? '(2 3) [1 2 3 4]) => false
      (prefix-of? '(1 2) [1 2 3 4]) => true)

(fact "tails"
      (tails '(1 2 3 4)) => '((1 2 3 4) (2 3 4) (3 4) (4) ()))
