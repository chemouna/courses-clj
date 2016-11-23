(ns courses-clj.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

;(def app-state (atom {:text "Tutorials!"}))
;(def app-state (atom {:list ["lion" "zebra" "Buffalo" "Antelope"]}))

;; (defn root-component [app owner]
;;   (reify
;;     om/IRender
;;     (render [_]
;;       (dom/div nil (dom/h2 nil (:text b
;; (om/root
;;  root-component
;;  app-state
;;  {:target (js/document.getElementById "app")})

;; (defn stripe [text bgc]
;;   (let [st #js {:backgroundColor bgc}]
;;     (dom/li #js {:style st} text)))

;; (om/root
;;  (fn [data owner]
;;    (om/component
;;     (apply dom/ul #js {:classname "animals"}
;;            (map stripe (:list data) (cycle ["#ff0" "#ff"])))))
;;  app-state
;;  {:target (js/document.getElementById "app")})

(defonce app-state
  (atom
   {:contacts
    [{:first "Ben" :last "Bitdiddle" :email "benb@mit.edu"}
     {:first "Alyssa" :middle-initial "P" :last "Hacker" :email "aphacker@mit.edu"}
     {:first "Eva" :middle "Lu" :last "Ator" :email "eval@mit.edu"}
     {:first "Louis" :last "Reasoner" :email "prolog@mit.edu"}
     {:first "Cy" :middle-initial "D" :last "Effect" :email "bugs@mit.edu"}
     {:first "Lem" :middle-initial "E" :last "Tweakit" :email "morebugs@mit.edu"}]}))

(defn middle-name [{:keys [middle middle-initial]}]
  (cond
    middle (str " " middle)
    middle-initial (str " " middle-initial ".")))

(defn display-name [{:keys [first last] :as contact}]
  (str last ", " first (middle-name contact)))

(defn contact-view [contact owner]
  (reify
    om/IRender
    (render [this]
      (dom/li nil
              (dom/span nil (display-name contact))
              (dom/button nil "Delete")))))

(defn contacts-view [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/div nil
               (dom/h2 nil "Contact list")
               (apply dom/ul nil
                      (om/build-all contact-view (:contacts data)))))))

(om/root contacts-view app-state
         {:target (js/document.getElementById "contacts")})
