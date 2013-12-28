(ns fp_oo.ch4)

(def make
  (fn [ctor & args]
      (apply ctor args)))

(def send-to
  (fn [obj msg & args]
    (apply (msg (:__methods__ obj)) obj args)))

(def Point
  (fn [x y]
      {:x x
      :y y
      :__class_symbol__ 'Point
      :__methods__
      {:shift 
        (fn [this xinc yinc]
          (make Point (+ (send-to this :x) xinc) (+ (send-to this :y) yinc)))
       :add
        (fn [this other]
          (send-to this :shift (send-to other :x) (send-to other :y)))
       :x (fn [this] (:x this))
       :y (fn [this] (:y this))}
       }))

(def class-of :__class_symbol__)
