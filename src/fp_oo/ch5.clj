(ns fp_oo.ch5)

(def apply-message-to
  (fn [class obj msg & args]
    (let [method (msg (:__instance_methods__ class))]
      (if (nil? method)
       (msg obj)
       (apply method obj (first args))))))

(def make
  (fn [class & args]
      (let [seeded { :__class_symbol__ (:__own_symbol__ class) } ]
        (apply-message-to class seeded :add-instance-values args))))

(def send-to
  (fn [obj msg & args]
    (let [class (eval (:__class_symbol__ obj)) ]
      (apply-message-to class obj msg args))))

(def Point
      {:__own_symbol__ 'Point
      :__instance_methods__
      {
       :add-instance-values
       (fn [this x y]
         (assoc this :x x :y y))

       :class-name :__class_symbol__

       :class
       (fn [this]
         (eval (:__class_symbol__ this)))

       :shift
        (fn [this xinc yinc]
          (make Point (+ (send-to this :x) xinc) (+ (send-to this :y) yinc)))

       :add
        (fn [this other]
          (send-to this :shift (send-to other :x) (send-to other :y)))
       }
       })
