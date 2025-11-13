import { Component, inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { StudentService } from '../../services/student.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-student',
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSnackBarModule,
  ],
  templateUrl: './add-student.component.html',
  styleUrl: './add-student.component.css',
})
export class AddStudentComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly studentService = inject(StudentService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);
  public isEditMode: boolean = false;
  private studentId: string | null = null;


  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const idParam = params['id'];

      if (idParam) {
        this.isEditMode = true;
        this.studentId = idParam;
        this.loadStudent(this.studentId!)
      }
    });
  }

  form: FormGroup = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    studentCode: ['', [Validators.required]],
    dateOfBirth: ['', Validators.required],
  });

  onSubmit() {
    if (this.form.invalid) return;


    if (this.isEditMode && this.studentId) {
      this.studentService.updateStudent(this.studentId, this.form.value)
        .subscribe({
          next: () => {
            this.router.navigate(['/students'])
          },
        });

    } else {
      this.studentService.addStudent(this.form.value).subscribe({
        next: () => {
          this.router.navigate(['/students']);
        },
      });
    }
  }

  onCancel() {
    this.router.navigate(['/students']);
  }

  loadStudent(id: string) {
    this.studentService.findStudentById(id)
      .subscribe(student => {
        this.form.patchValue({
          firstName: student.firstName,
          lastName: student.lastName,
          studentCode: student.studentCode,
          dateOfBirth: student.dateOfBirth,
        })
      });
  }
}
