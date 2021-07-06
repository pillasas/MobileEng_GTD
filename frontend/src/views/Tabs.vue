<template>
  <ion-page>
    <ion-tabs>
      <ion-router-outlet></ion-router-outlet>
      <ion-tab-bar slot="bottom">
        <ion-tab-button tab="timeTodo" href="/tabs/timeTodo">
          <ion-icon :icon="flash" />
          <ion-label>Aktuell</ion-label>
        </ion-tab-button>
        
        <ion-tab-button tab="priority" href="/tabs/priority">
          <ion-icon :icon="send" />
          <ion-label>Priorität</ion-label>
        </ion-tab-button>
                  
        <ion-tab-button tab="projects" href="/tabs/projects">
          <ion-icon :icon="apps" />
          <ion-label>Projects</ion-label>
        </ion-tab-button>

      </ion-tab-bar>
    </ion-tabs>
  </ion-page>
</template>

<script lang="ts">
import { IonTabBar, IonTabButton, IonTabs, IonLabel, IonIcon, IonPage, IonRouterOutlet } from '@ionic/vue';
import { flash, apps, send } from 'ionicons/icons';

export default {
  name: 'Tabs',
  components: { IonLabel, IonTabs, IonTabBar, IonTabButton, IonIcon, IonPage, IonRouterOutlet },
  setup() {
    return {
      flash, 
      apps, 
      send,
    }
  }
}
</script>