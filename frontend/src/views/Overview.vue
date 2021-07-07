<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Übersicht</ion-title>
        <ion-segment value="time">
          <ion-segment-button @click="loadTimedTasks" value="time"
            >Anstehend</ion-segment-button
          >
          <ion-segment-button @click="loadPrioritisedTasks" value="prioritised"
            >Priorisiert</ion-segment-button
          >
        </ion-segment>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <ion-header collapse="condense"> </ion-header>
      <tasklist v:bind:tasks="todos"></tasklist>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonSegment,
  IonSegmentButton,
} from "@ionic/vue";
import App from "@/App.vue";
import Tasklist from "@/components/Tasklist.vue";
import { useTodos } from "@/composables/useTodos";
import { defineComponent } from "@vue/runtime-core";

export default defineComponent({
  name: "Overview",
  components: {
    IonContent,
    IonPage,
    IonHeader,
    IonToolbar,
    IonTitle,
    IonSegment,
    IonSegmentButton,
    Tasklist
  },
  mounted() {
    this.getTodosByTime();
  },
  setup() {
    const { todos, getTodosByTime, getTodosByPriority } = useTodos();
    return { todos, getTodosByTime, getTodosByPriority };
  },
  methods: {
    loadPrioritisedTasks() {
      this.getTodosByPriority();
    },

    loadTimedTasks() {
      this.getTodosByTime();
    },
  },
});
</script>