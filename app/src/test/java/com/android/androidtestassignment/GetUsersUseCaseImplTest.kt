package com.android.androidtestassignment

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.android.androidtestassignment.api.datamodel.Coordinates
import com.android.androidtestassignment.api.datamodel.Dob
import com.android.androidtestassignment.api.datamodel.Id
import com.android.androidtestassignment.api.datamodel.Location
import com.android.androidtestassignment.api.datamodel.Login
import com.android.androidtestassignment.api.datamodel.Name
import com.android.androidtestassignment.api.datamodel.Picture
import com.android.androidtestassignment.api.datamodel.Registered
import com.android.androidtestassignment.api.datamodel.Street
import com.android.androidtestassignment.api.datamodel.Timezone
import com.android.androidtestassignment.api.datamodel.UserModel
import com.android.androidtestassignment.repositories.UsersRepository
import com.android.androidtestassignment.ui.datamodels.User
import com.android.androidtestassignment.usecases.GetUsersUseCase
import com.android.androidtestassignment.usecases.GetUsersUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.ZoneId
import java.time.ZonedDateTime

@ExperimentalCoroutinesApi
class GetUsersUseCaseImplTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var usersRepository: UsersRepository

    private lateinit var getUsersUseCase: GetUsersUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        getUsersUseCase = GetUsersUseCaseImpl(usersRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke - success`() = runTest {
        // Arrange
        val userModel = UserModel(
                gender = "male",
                name = Name(
                    title = "Mr",
                    first = "Zhadan",
                    last = "Musiienko"
                ),
                location = Location(
                    street = Street(
                        number = 150,
                        name = "Begoulieva"
                    ),
                    city = "Rzhishchiv",
                    state = "Mikolayivska",
                    country = "Ukraine",
                    postcode = 43271,
                    coordinates = Coordinates(
                        latitude = "-84.4183",
                        longitude = "153.8337"
                    ),
                    timezone = Timezone(
                        offset = "+9:30",
                        description = "Adelaide, Darwin"
                    )
                ),
                email = "zhadan.musiienko@example.com",
                login = Login(
                    uuid = "0cdd435a-6b4d-4a7f-8310-85c485b1e853",
                    username = "brownleopard588",
                    password = "redrose",
                    salt = "4pukcxoY",
                    md5 = "b4374f54cc8b5312098517f0e78c16ce",
                    sha1 = "4e4226f672e6167c63776504d3e7af76f3e22025",
                    sha256 = "1281060a33ea21dd5c9724930fa4542272d207e52a69199b9a065ce735ea3786"
                ),
                dob = Dob(
                    date = "1967-08-09T19:14:09.210Z",
                    age = 57
                ),
                registered = Registered(
                    date = "2018-02-13T06:45:20.777Z",
                    age = 7
                ),
                phone = "(097) I26-7373",
                cell = "(099) C94-7448",
                id = Id(
                    name = null,
                    value = null
                ),
                picture = Picture(
                    large = "https://randomuser.me/api/portraits/men/9.jpg",
                    medium = "https://randomuser.me/api/portraits/med/men/9.jpg",
                    thumbnail = "https://randomuser.me/api/portraits/thumb/men/9.jpg"
                ),
                nat = "UA"
            )

        val expectedPagingData = PagingData.from(listOf(userModel))
        `when`(usersRepository.getUsers()).thenReturn(flowOf(expectedPagingData))

        // Act
        val result = getUsersUseCase().asSnapshot()

        // Assert
        val expectedUser = User(
            id = userModel.login.uuid,
            name = "${userModel.name.first} ${userModel.name.last}",
            country = userModel.nat,
            profilePictureUrl = userModel.picture.thumbnail,
            age = userModel.dob.age,
            time = ZonedDateTime.parse(userModel.registered.date)
                .withZoneSameInstant(ZoneId.systemDefault())
        )
        val actualUser = result.map { it }.first()
        assertEquals(expectedUser, actualUser)
    }

    @Test
    fun `invoke - empty list`() = runTest {
        // Arrange
        val expectedPagingData = PagingData.empty<UserModel>()
        `when`(usersRepository.getUsers()).thenReturn(flowOf(expectedPagingData))

        // Act
        val result = getUsersUseCase().asSnapshot()

        // Assert
        assertEquals(emptyList<User>(), result)
    }
}