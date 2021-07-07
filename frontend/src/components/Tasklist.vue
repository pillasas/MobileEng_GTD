<template>
  <ion-page>
    <ion-content>
      <ion-list>
        <task-list-item
          v-bind="task"
          v-bind:key="task"
          v-for="task in tasks"
        ></task-list-item>
      </ion-list>
      <ion-fab slot="fixed" vertical="bottom" horizontal="end">
        <ion-fab-button>
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
      </ion-fab>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import TaskListItem from "@/components/TasklistItem.vue";
import { IonContent, IonPage, IonList, IonFab, IonFabButton, IonIcon } from "@ionic/vue";
import { defineComponent } from "vue";
import { add } from 'ionicons/icons';


export default defineComponent({
  name: "Tasklist",
  components: {
    IonContent,
    IonPage,
    IonList,
    TaskListItem,
    IonFab,
    IonFabButton,
    IonIcon
  },
  props: ["tasks"],
   setup() {
    return {
     add
    }
  }
});
</script>

<style scoped>
</style>