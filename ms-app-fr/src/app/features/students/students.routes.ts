import { Routes } from '@angular/router';
import { ListStudentsComponent } from './components/list-students/list-students.component';
import { AddStudentComponent } from './components/add-student/add-student.component';

export const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'list',
        component: ListStudentsComponent
      },
      {
        path: 'add',
        component: AddStudentComponent
      },
      {
        path: '**',
        redirectTo: 'list'
      }
    ]
  }
];


