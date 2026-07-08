import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { Person } from '../../models/person';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-add-person',
  imports: [FormsModule, RouterLink],
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
    this.personService.add(this.person).subscribe(() => {
      this.router.navigateByUrl('/');
    });
  }
}
