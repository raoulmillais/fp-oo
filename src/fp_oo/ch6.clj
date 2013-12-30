(ns fp_oo.ch6)

(def class-symbol-above
  (fn [class-symbol]
    (assert (symbol? class-symbol))
    (:__superclass_symbol__ (eval class-symbol))))

(def inheritance
  (fn [class-symbol]
    (if (nil? class-symbol)
      nil
      (cons  class-symbol
            (inheritance (class-symbol-above class-symbol))))))

(def lineage
  (fn [class-symbol]
    (assert (symbol? class-symbol))
    (reverse (inheritance class-symbol))))

(def class-instance-methods
  (fn [class-symbol]
    (assert (symbol? class-symbol))
    (:__instance_methods__ (eval class-symbol))))

(def method-cache
  (fn [class]
    (let [class-symbol (:__own_symbol__ class)
          method-maps (map class-instance-methods (lineage class-symbol))]
      (apply merge method-maps))))

(def class-from-instance
  (fn [obj]
    (assert (map? obj))
    (eval (:__class_symbol__ obj))))

(def apply-message-to
  (fn [class obj msg & args]
    (let [method (msg (method-cache class))]
      (if (nil? method)
       (msg obj)
       (apply method obj (first args))))))

(def make
  (fn [class & args]
      (let [seeded { :__class_symbol__ (:__own_symbol__ class) } ]
        (apply-message-to class seeded :add-instance-values args))))

(def send-to
  (fn [obj msg & args]
    (let [class (class-from-instance obj) ]
      (apply-message-to class obj msg args))))

(def Point
      {:__own_symbol__ 'Point
       :__superclass_symbol__ 'Anything
      :__instance_methods__
      {
       :add-instance-values
       (fn [this x y]
         (assoc this :x x :y y))

       :shift
        (fn [this xinc yinc]
          (make Point (+ (send-to this :x) xinc) (+ (send-to this :y) yinc)))

       :add
        (fn [this other]
          (send-to this :shift (send-to other :x) (send-to other :y)))
       }
       })

(def Anything
  {:__own_symbol__ 'Anything
   :__instance_methods__
   {
    ;; default constructor
    :add-instance-values identity

    :class-name :__class_symbol__

    :class class-from-instance

    }})

(def factorial-trad
  (fn [n]
    (if (or (= n 0) (= n 1))
      1
      (* n (factorial-trad (- n 1))))))

(def factorial-tail1
  (fn [n so-far]
    (if (or (= 0 n) (= 1 n))
      so-far
      (recur (- n 1) (* n so-far)))))

(def factorial-tail
  (fn [n] (factorial-tail1 n 1)))

(def recursive-function
  (fn [combiner l so-far]
    (if (empty? l)
      so-far
      (recur combiner (rest l) (combiner (first l) so-far)))))

(def add-tail
  (fn [l so-far]
    (if (empty? l)
      so-far
      (recur (rest l) (+ so-far (first l))))))

(def multiply-tail
  (fn [l so-far]
    (recursive-function * l so-far)))
