<template>
  <ion-item-sliding>
    <ion-item
      button
      v-bind:router-link="'/tabs/overview/' + todoItem.id"
      v-bind:key="todoItem"
    >
      <ion-label>
        <h2>{{ todoItem.title }}</h2>
        <h3>
          {{
            new Date(todoItem.zeitpunkt).toLocaleDateString("de-DE", {
              hour12: false,
              hour: "2-digit",
              minute: "2-digit",
            })
          }}
        </h3>
        <p>{{ todoItem.beschreibung }}</p>
      </ion-label>
    </ion-item>
    <ion-item-options side="end">
      <ion-item-option @click="deleteTodo()">Löschen</ion-item-option>
    </ion-item-options>
  </ion-item-sliding>
</template>

<script lang="ts">
import { useTodos } from "@/composables/useTodos";
import { useRoute } from "vue-router";
import {
  IonItem,
  IonItemSliding,
  IonLabel,
  IonItemOptions,
  IonItemOption,
} from "@ionic/vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "ToDoListItem",
  components: {
    IonItem,
    IonItemSliding,
    IonLabel,
    IonItemOptions,
    IonItemOption,
  },
  props: ["todoItem"],
  setup() {
    const { removeTodo, getTodos } = useTodos();
    return {removeTodo, getTodos };
  },
  methods: {
    deleteTodo(){
      this.removeTodo(this.todoItem);
      // this.getTodos();
      this.$router.go(0);
    }
  }
});
</script>

<style scoped>
</style>