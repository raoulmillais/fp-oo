(ns fp_oo.ch3)

(def point {:x 1, :y 2, :__class_symbol__ 'Point})

(def Point
  (fn [x y]
    {:x x
     :y y
     :__class_symbol__ 'Point}))

(def x
  (fn [this]
    (:x this)))

(def y :y)

(def class-of :__class_symbol__)

(def shift
  (fn [this xinc yinc]
    (Point (+ (x this) xinc)
           (+ (y this) yinc))))

(def add
  (fn [this other]
    (shift this (x other) (y other))))

(def add-points
  (fn [p1 p2]
    (Point (+ (x p1) (x p2))
           (+ (y p1) (y p2)))))

(def Triangle
     (fn [point1 point2 point3]
       {:point1 point1, :point2 point2, :point3 point3
        :__class_symbol__ 'Triangle}))

(def right-triangle (Triangle (Point 0 0)
                              (Point 0 1)
                              (Point 1 0)))

(def equal-right-triangle (Triangle (Point 0 0)
                                    (Point 0 1)
                                    (Point 1 0)))

(def different-triangle (Triangle (Point 0 0)
                                  (Point 0 10)
                                  (Point 10 0)))

(def make
  (fn [ctor & args]
    (apply ctor args)))

(def equal-triangles?
  (fn [t1 t2]
    (= t1 t2)))

(def equal-triangles2? =)

(def valid-triangle?
  (fn [& args]
    (= (count (distinct args)) 3)))
