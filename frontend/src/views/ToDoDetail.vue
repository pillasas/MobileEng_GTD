<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button></ion-back-button>
        </ion-buttons>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <!-- Titel -->
      <ion-item>
        <ion-label>{{editTodo.title}}</ion-label>
        <ion-input></ion-input>
      </ion-item>
      <!-- Kategorie -->
      <ion-item>
        <ion-label>Kategorie</ion-label>
        <ion-select interface="popover">
          <ion-select-option value="nes">NES</ion-select-option>
          <ion-select-option value="n64">Nintendo64</ion-select-option>
          <ion-select-option value="ps">PlayStation</ion-select-option>
          <ion-select-option value="genesis">Sega Genesis</ion-select-option>
          <ion-select-option value="saturn">Sega Saturn</ion-select-option>
          <ion-select-option value="snes">SNES</ion-select-option>
        </ion-select>
      </ion-item>
      <!-- Zeitpunkt -->
      <ion-item>
        <ion-label>Datum</ion-label>
        <ion-datetime
          value={{editTodo.zeitpunkt}}
          placeholder="Select Date"
        ></ion-datetime>
      </ion-item>
      <ion-item>
        <ion-label>Zeit</ion-label>
        <ion-datetime
          display-format="h:mm A"
          picker-format="h:mm A"
          value={{editTodo.zeitpunkt}}
        ></ion-datetime>
      </ion-item>
      <!-- Priorität -->
      <ion-item>
        <ion-label>Priorität</ion-label>
        <ion-select interface="popover">
          <ion-select-option value="1">NES</ion-select-option>
          <ion-select-option value="2">Nintendo64</ion-select-option>
          <ion-select-option value="3">PlayStation</ion-select-option>
        </ion-select>
      </ion-item>
      <!-- Beschreibung -->
      <ion-item>
        <ion-label>Beschreibung</ion-label>
        <ion-textarea>{{editTodo.beschreibung}}</ion-textarea>
      </ion-item>
      <p>Task-Detail for ID {{ id }}</p>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonButtons,
  IonBackButton,
  IonContent,
  IonItem,
  IonLabel,
  IonInput,
  IonTextarea,
  IonSelect,
  IonSelectOption,
  IonDatetime,
} from "@ionic/vue";
import { defineComponent } from "vue";
import { useRoute } from "vue-router";
import { useTodos } from "@/composables/useTodos";

export default defineComponent({
  components: {
    IonPage,
    IonHeader,
    IonToolbar,
    IonButtons,
    IonBackButton,
    IonContent,
    IonItem,
    IonLabel,
    IonInput,
    IonTextarea,
    IonSelect,
    IonSelectOption,
    IonDatetime,
  },
  props: ["todoEdit"],
  mounted() {
    
    this.getTodoByTodoId(Number(this.id));
    
  },
  setup() {
    const route = useRoute();
    const { editTodo, getTodoByTodoId } = useTodos();
    //Id muss gleich heissen wie im router definiert
    const { id } = route.params;
    return { id, editTodo, getTodoByTodoId  };
  },
});
</script>

<style>
</style>