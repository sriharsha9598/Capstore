import { TestBed } from '@angular/core/testing';

import { UserService1Service } from './user-service1.service';

describe('UserService1Service', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserService1Service = TestBed.get(UserService1Service);
    expect(service).toBeTruthy();
  });
});
