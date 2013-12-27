(ns fp_oo.ch1)

;; 1.10 Lists
;; Exercise 1
(def second 
  (fn [l] (nth l 1)))

;; Exercise 2
(def third-nth
  (fn [l] (nth l 2)))

(def third-rest
  (fn [l]
    (first (rest (rest l)))))

;; 1.18
;; Exercise 3
(def add-squares
  (fn [& args]
    (apply + (map * args args))))

;; Exercise 4
(def range-fac
  (fn [n]
    (apply * (range 1 (inc n)))))

;; Exercise 5
(def first-5
  (fn [l]
    (take 5 l)))

(def count-duplicates
  (fn [l]
    (- (count l)
       (count (distinct l)))))

(def odds-and-evens 
  (fn [l1 l2]
    (concat (filter odd? l1)
            (filter even? l2))))

(def repeat-range
  (fn [n]
    (map 
      (fn [m] 
        (repeat m m)) 
      (range 1 (inc n)))))

(def star-struck
  (fn [l]
    (interleave l
                (repeat (count l) '*))))

(def chop-shop
  (fn [l1 l2]
    (concat (drop-last (quot (count l1) 2) l1)
            (drop (quot (count l2) 2) l2))))

(def ranges
  (fn [l]
    (flatten (map (fn [n] (repeat n n)) l))))

(def pairs
  (fn [l] (partition 2 l)))

(def only-odd-numbers?
  (fn [l] (every? odd? l)))

(def strip-prefixed
  (fn [prefix l]
    (remove (fn [i] (.startsWith i prefix)) l)))

(def prefix-of?
  (fn [candidate sequence]
    (= (take (count candidate) sequence)
       candidate)))

(def tails
  (fn [l]
    (if
      (empty? l) '(())
      (cons l (tails (rest l))))))
