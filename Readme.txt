1.Controller
2.Dto - req dto/response dto
        Conroller-@RequestBody RequestDoctorDto doctorDto

3.Repo(interface) extend JPA repo
4.Entity
5.Service(interface)
6.Service-impl
7.Service => Controller(private final DoctorService doctorService;)
8.Util - Standard Response
9.Paginated - PaginatedDoctorResponseDto
10.Advisers - AppWiderExceptionHandler
11.Exception - EntryNotFoundException(Extend RunTimeExceptio

(MapStruct) Update PomXML

12.Mapper

13.Spring Security - JWT(User,UserRole,UserRoleHasUser,UserRoleHasUserKey)