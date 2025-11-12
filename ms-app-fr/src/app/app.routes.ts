import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'students',
    loadChildren: () => import('./features/students/students.routes').then(m => m.routes)
  },
  {
    path: '**',
    redirectTo: 'students'
  }
];
