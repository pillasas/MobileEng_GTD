<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button></ion-back-button>
        </ion-buttons>
        <ion-title>{{ editTodo.title }}</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <!-- Titel -->
      <ion-item>
        <ion-label>Title</ion-label>
        <ion-input v-model="editTodo.title"></ion-input>
      </ion-item>
      <!-- Kategorie (not implemented)-->
      <!-- <ion-item>
        <ion-label>Kategorie</ion-label>
        <ion-select interface="popover">
          <ion-select-option value="nes">NES</ion-select-option>
          <ion-select-option value="n64">Nintendo64</ion-select-option>
          <ion-select-option value="ps">PlayStation</ion-select-option>
          <ion-select-option value="genesis">Sega Genesis</ion-select-option>
          <ion-select-option value="saturn">Sega Saturn</ion-select-option>
          <ion-select-option value="snes">SNES</ion-select-option>
        </ion-select>
      </ion-item> -->
      <!-- Zeitpunkt -->
      <ion-item>
        <ion-label>Datum</ion-label>
        <ion-datetime
          v-model="editTodo.zeitpunkt" v-bind:value="editTodo.zeitpunkt"
          placeholder="Select Date"
        ></ion-datetime>
      </ion-item>
      <ion-item>
        <ion-label>Zeit</ion-label>
        <ion-datetime
          display-format="h:mm A"
          picker-format="h:mm A"
          v-bind:value="editTodo.zeitpunkt"
        ></ion-datetime>
      </ion-item>
      <!-- Priorität -->
      <ion-item>
        <ion-label>Priorität</ion-label>
        <ion-select interface="popover" v-model="selectedPrio">
          <ion-select-option :value="1">Hoch</ion-select-option>
          <ion-select-option :value="2">Mittel</ion-select-option>
          <ion-select-option :value="3">Tief</ion-select-option>
        </ion-select>
      </ion-item>
      <!-- Beschreibung -->
      <ion-item>
        <ion-label>Beschreibung</ion-label>
        <ion-textarea v-model="editTodo.beschreibung"></ion-textarea>
      </ion-item>
      <!-- Done -->
      <ion-item>
        <ion-label>Erledigt</ion-label>
        <ion-toggle name="done" v-bind:checked="editTodo.done"></ion-toggle>
      </ion-item>
    </ion-content>
    <ion-footer>
      <section class="full-width">
        <ion-button @click="saveTodo" expand="full" color="secondary"
          >Speichern</ion-button
        >
      </section>
    </ion-footer>
  </ion-page>
</template>

<script lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonButtons,
  IonButton,
  IonBackButton,
  IonContent,
  IonItem,
  IonLabel,
  IonInput,
  IonTextarea,
  IonSelect,
  IonSelectOption,
  IonDatetime,
  IonFooter,
  IonToggle,
  IonTitle,
} from "@ionic/vue";
import { defineComponent, ref } from "vue";
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
    IonFooter,
    IonButton,
    IonToggle,
    IonTitle,
  },
  props: ["todoEdit"],
  async mounted() {
    if (this.id !== "newTodo") {
      await this.getTodoByTodoId(Number(this.id));
    } 
    this.selectedPrio = this.editTodo.priorisierung as number;
  },
  setup() {
    const route = useRoute();
    const { editTodo, getTodoByTodoId, finishTodo, addTodo } = useTodos();
    //Id muss gleich heissen wie im router definiert
    const { id } = route.params;

    return { id, editTodo, getTodoByTodoId, finishTodo, addTodo };
  },
  methods: {
   async saveTodo() {
      if (this.id == "newTodo") {
       await this.addTodo(this.editTodo);
      } else {
        this.finishTodo(this.editTodo);
      }
      this.$router.push({ name: "overview"})
      // this.$router.go(0)
    },
  },
  data() {
    return {
      selectedPrio: 0,
    };
  },
});
</script>

<style>
</style>