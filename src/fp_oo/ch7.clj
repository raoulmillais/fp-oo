(ns fp_oo.ch7)

(def answer-annotations
  (fn [courses registrant-courses]
    (let [checking-set (set registrant-courses)]
      (map (fn [course]
             (assoc course
                    :already-in? (contains? checking-set
                                            (:course-name course))
                    :spaces-left (- (:limit course) (:registered course))))
           courses))))

(def domain-annotations
  (fn [courses]
    (map (fn [course]
           (assoc course
                  :full? (zero? (:spaces-left course))
                  :empty? (zero? (:registered course)))) courses)))

(def note-unavailability
  (fn [courses instructor-count]
    (let [out-of-instructors?
          (= instructor-count
             (count (filter (fn [course] (not (empty? course)))
                    courses)))]
      (map (fn [course] 
             (assoc course
                    :unavailable? (or out-of-instructors?
                                      (:full? course))))
           courses))))

(def annotate
  (fn [courses registrant-courses instructor-count]
    (-> courses
        (answer-annotations registrant-courses)
        domain-annotations
        (note-unavailability instructor-count))))

(def separate
  (fn [predicate sequence]
    [(filter predicate sequence) (remove predicate sequence)]))

(def visible-courses
  (fn [courses]
    (let [[guaranteed possibilities] (separate :already-in? courses)]
      (concat guaranteed (remove :unavailable? possibilities)))))

(def ex-1
  (fn [v]
    (-> v
        first
        inc
        list)))

(def ex-2
  (fn [v]
    (-> v
        first
        inc
        (* 3)
        list)))

(def ex-3
  (fn [n]
    (-> n ((fn [n] (* 2 n))) inc)))
