import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

import { Person } from '../../models/person';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-add-person',
  imports: [FormsModule, RouterLink, MatButtonModule, MatCardModule, MatFormFieldModule, MatInputModule],
  templateUrl: './add-person.component.html',
  styleUrl: './add-person.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AddPersonComponent {
  private readonly personService = inject(PersonService);
  private readonly router = inject(Router);

  person: Person = {
    address: {}
  };

  save(): void {
    this.personService.add(this.person);
    this.router.navigateByUrl('/');
  }
}
